package com.sanvui.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author: VuiSK
 * @created: 26/11/2021-3:00 PM
 * @mailto: sanvankhanh@gmail.com
 */

public final class DateTimeUtil {
    public static Long convertToUnixTime(LocalDateTime dateTime){
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
