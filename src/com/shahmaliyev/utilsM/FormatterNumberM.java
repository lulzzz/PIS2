/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.math3.fraction.BigFraction;

/**
 *
 * @author mamed.shahmaliyev
 */
public class FormatterNumberM {
    public static String numberFormat = "#.################";
    public static String formatNumber(double a){
        DecimalFormat df = new DecimalFormat(numberFormat);
        return df.format(a);
    }
    public static String formatNumber(DynamicNumber a){
        DecimalFormat df = new DecimalFormat(numberFormat);
        return df.format(a.doubleValue());
    }
    public static String formatNumber(BigFraction a){
        DecimalFormat df = new DecimalFormat(numberFormat);
        return df.format(a.doubleValue());
    }
    public static String formatNumber(BigDecimal a){
        DecimalFormat df = new DecimalFormat(numberFormat);
        return df.format(a.doubleValue());
    }
    
    
    public static BigFraction strToBigFraction(String str){
        String t[] = str.split("/");
        try {
            if (t.length == 1) return new BigFraction(Double.parseDouble(str));
            else if (t.length == 2) return new BigFraction(Integer.parseInt(t[0]),Integer.parseInt(t[1]));
            else return BigFraction.ZERO;
        } catch (Exception e) {
            return BigFraction.ZERO;
        }
    }
    public static double strToDouble(String str){
        String t[] = str.split("/");
        try {
            if (t.length == 1) return new BigFraction(Double.parseDouble(str)).doubleValue();
            else if (t.length == 2) return new BigFraction(Integer.parseInt(t[0]),Integer.parseInt(t[1])).doubleValue();
            else return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    public static Integer strToInteger(String str){
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }
}
