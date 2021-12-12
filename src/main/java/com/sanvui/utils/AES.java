package com.sanvui.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

/**
 * @author: VuiSK
 * @created: 03/11/2021-8:29 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    /*
     *encrypt myKey
     * return

     */
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    encrypt
    *param: String can encrypt and String key secret
    * return String encrypted if error return null
    *
 */

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /*
    encrypt
    *param: String can encrypt and String key secret
    * return String decrypted if error return null
    *
 */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String getSecret() {

        Properties properties = new Properties();
        InputStream inputStream = null;

        String secretKey = null;

        try {

            inputStream = new FileInputStream("src/main/resources/secretKey.properties");

//            load properties from file
            properties.load(inputStream);

//            get properties by name
            secretKey = properties.getProperty("key");

        } catch (IOException e) {
            System.out.println("Get secretKey fail!");
            return null;
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return secretKey;
    }
}