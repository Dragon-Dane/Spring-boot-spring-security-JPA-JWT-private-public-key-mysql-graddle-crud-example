package com.amran.user.module;

import java.security.*;
import java.util.Base64;

/**
 * @Author : Amran Hosssain on 6/16/2020
 */
public class KeyGenerator {

    public static void main(String[] args) {
        try {

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024); // You can set a different value here

            KeyPair kp = kpg.generateKeyPair();
            PrivateKey prk = kp.getPrivate();
            PublicKey puk = kp.getPublic();

            Base64.Encoder enc = Base64.getEncoder();

            System.out.println();
            System.out.println("- --- ( RSA Key Pair Generator ) --- -");
            System.out.println("Private Key: " + enc.encodeToString(prk.getEncoded()));
            System.out.println();
            System.out.println("Public Key: " + enc.encodeToString(puk.getEncoded()));
            System.out.println();
            System.out.println("--- End ---");

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }
}
