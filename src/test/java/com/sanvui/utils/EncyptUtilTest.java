package com.sanvui.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: VuiSK
 * @created: 26/11/2021-3:51 PM
 * @mailto: sanvankhanh@gmail.com
 */

class EncyptUtilTest {

    @Test
    void hashText() {
        String ss="$2a$10$py2PLFkrdnOv.29WHBUo5OllPFkuC53Yq5rHqhcPU2k2SjYMG.kqG";
        String s = passwordEncoder().encode("Vui@23102000z");
        boolean valuate = BCrypt.checkpw("sankhanh", "$2a$10$py2PLFkrdnOv.29WHBUo5OllPFkuC53Yq5rHqhcPU2k2SjYMG.kqG");
        System.out.println(s.equals(ss));
        System.out.println(s);
        System.out.println(valuate);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}