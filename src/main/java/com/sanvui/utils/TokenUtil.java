package com.sanvui.utils;

import com.sanvui.model.entity.Employee;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 04/12/2021-2:25 PM
 * @mailto: sanvankhanh@gmail.com
 */

public final class TokenUtil {

    public static String genericActiveToken(Employee employee, String key, int time){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.plusMinutes(time);
        return AES.encrypt(EncyptUtil.passwordEncoder().encode(employee.getUserName()+employee.getEmail())+"time"+dateTime, key);
    }
}
