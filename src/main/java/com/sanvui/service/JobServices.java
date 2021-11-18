package com.sanvui.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: VuiSK
 * @created: 04/11/2021-1:18 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class JobServices {
    private static LocalDate localDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }
}
