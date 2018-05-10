package com.smu.vaan.utils;

import java.math.BigDecimal;

/**
 * Created by vaan on 2017/3/21.
 */
public class NumericUtil {
    public static float round(float number, int decimal) {
        BigDecimal bg = new BigDecimal(number);
        return  bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static double round(double number, int decimal) {
        BigDecimal bg = new BigDecimal(number);
        return  bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
