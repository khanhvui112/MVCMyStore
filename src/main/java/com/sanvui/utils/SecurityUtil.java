package com.sanvui.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 11/12/2021-11:34 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class SecurityUtil {
    public static String getIdCurrentUserLogin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
        }catch (Exception e) {
            return null;
        }
        return  Objects.nonNull(userDetails) ? userDetails.getUsername() : null;
    }

    public static String getPasswordCurrentUserLogin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
        }catch (Exception e) {
            return null;
        }
        return  Objects.nonNull(userDetails) ? userDetails.getPassword() : null;
    }
}
