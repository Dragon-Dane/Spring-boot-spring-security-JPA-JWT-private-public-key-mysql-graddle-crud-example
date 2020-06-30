package com.amran.user.module.util;

import com.amran.user.module.constant.JWTConstants;
import com.amran.user.module.service.UserTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

/**
 * @Author : Amran Hosssain on 6/16/2020
 */
@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private UserTokenService userTokenService;

    private static final long serialVersionUID = -2550185165626007488L;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //Extract information from existing token. for verification
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getPublicKeyFromFile())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String userName) {
        String generatedToken = doGenerateToken(userName);
        //Token Stored in database for proper logout operation execute
        userTokenService.saveOrUpdateTokenIntoDatabase(userName, generatedToken);
        return generatedToken;
    }

    //Generate Token Based on some configuration
    private String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject).setAudience("user_module");
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("system")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.RS512, getPrivateKeyFromFile())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        //Just check token have in the database or not if found the execute operation otherwise return false;
        if (userTokenService.isExistTokenByUserName(userDetails.getUsername())) {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
        return Boolean.FALSE;
    }

    //Make Private Key From classpath key.
    //private key used for JWT signing
    private PrivateKey getPrivateKeyFromFile() {
        Resource resource = resourceLoader.getResource("classpath:private.key");
        PrivateKey privateKey = null;
        try {
            InputStream inputStream = resource.getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(data));
            privateKey = kf.generatePrivate(keySpecPKCS8);
        } catch (Exception ex) {

        }
        return privateKey;
    }

    //Make Public Key From classpath key.
    //public key used for JWT verification. it's actually valid one where provided.
    private PublicKey getPublicKeyFromFile() {
        Resource resource = resourceLoader.getResource("classpath:public.key");
        RSAPublicKey publicKey = null;
        try {
            InputStream inputStream = resource.getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(data));
            publicKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        } catch (Exception ex) {

        }
        return publicKey;
    }
}
