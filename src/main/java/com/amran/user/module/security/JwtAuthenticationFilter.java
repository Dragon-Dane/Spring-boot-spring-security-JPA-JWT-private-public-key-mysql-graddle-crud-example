package com.amran.user.module.security;

import com.amran.user.module.constant.JWTConstants;
import com.amran.user.module.service.JwtUserDetailsService;
import com.amran.user.module.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author : Amran Hosssain on 6/16/2020
 * The class, OncePerRequestFilter, is a filter base class that aims to guarantee a single execution per request dispatch on any servlet container.
 * As of Servlet 3.0, a filter may be invoked as part of a REQUEST or ASYNC dispatch that occurs in separate threads.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //Every Request filter and checking authorization
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(JWTConstants.HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            authToken = header.replace(JWTConstants.TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException ex) {
                logger.error("An error during getting username from token", ex);
            } catch (ExpiredJwtException ex) {
                logger.warn("The token is expired and not valid anymore", ex);
            } catch(SignatureException ex){
                logger.error("Authentication Failed. Username or Password not valid.",ex);
            }
        } else {
            logger.warn("Couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
