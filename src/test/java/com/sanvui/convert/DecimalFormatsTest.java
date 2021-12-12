package com.sanvui.convert;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: VuiSK
 * @created: 10/12/2021-11:37 AM
 * @mailto: sanvankhanh@gmail.com
 */

class DecimalFormatsTest {

    @Test
    void decimals() {

        String s1 = NumberFormat.getNumberInstance(Locale.GERMAN).format(1000000);

        System.out.println(s1);
    }
}