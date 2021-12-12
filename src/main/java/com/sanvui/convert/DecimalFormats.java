package com.sanvui.convert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author: VuiSK
 * @created: 10/12/2021-11:31 AM
 * @mailto: sanvankhanh@gmail.com
 */

public final class DecimalFormats {
    public static String decimals(Double s){
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(s);
    }
}
