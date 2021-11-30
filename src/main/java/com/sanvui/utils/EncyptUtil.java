package com.sanvui.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 26/11/2021-3:02 PM
 * @mailto: sanvankhanh@gmail.com
 */

public final class EncyptUtil {

    private EncyptUtil() {
    }

    public static String hashText(String plainText) {
        Objects.requireNonNull(plainText);
        StringBuilder hashText = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(plainText.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashText = new StringBuilder(no.toString(16));

            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert hashText != null;
        return hashText.toString();
    }
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
