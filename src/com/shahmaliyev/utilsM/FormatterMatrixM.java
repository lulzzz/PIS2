/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.math3.fraction.BigFraction;
import static com.shahmaliyev.utilsM.UtilsM.getLineSeparator;

/**
 *
 * @author mamed.shahmaliyev
 */
public class FormatterMatrixM {
    public static String matrixToString(BigFraction[][] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != null) str += FormatterNumberM.formatNumber(a[i][j]);
                else str += "0";
                str += " ";
            }
            str += getLineSeparator();
        }
        return str;
    }
    public static String matrixToString(DynamicNumber[][] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != null) str += FormatterNumberM.formatNumber(a[i][j]);
                else str += "0";
                str += " ";
            }
            str += getLineSeparator();
        }
        return str;
    }
    public static String matrixToString(BigDecimal[][] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != null) str += FormatterNumberM.formatNumber(a[i][j]);
                else str += "0";
                str += " ";
            }
            str += getLineSeparator();
        }
        return str;
    }
    public static String matrixToString(double[][] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                str += FormatterNumberM.formatNumber(a[i][j]);
                str += " ";
            }
            str += getLineSeparator();
        }
        return str;
    }
    public static String matrixToString(BigFraction[] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) str += FormatterNumberM.formatNumber(a[i]);
            else str += "0";
            str += " ";
        }
        return str;
    }
    public static String matrixToString(DynamicNumber[] a,boolean formatted){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                if (formatted)str += FormatterNumberM.formatNumber(a[i]);
                else str += a[i].toString();
            }
            else str += "0";
            str += " ";
        }
        return str;
    }
    public static String matrixToString(DynamicNumber[] a){
        return matrixToString(a,false);
    }
    public static String matrixToString(BigDecimal[] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) str += FormatterNumberM.formatNumber(a[i]);
            else str += "0";
            str += " ";
        }
        return str;
    }
    public static String matrixToString(double[] a){
        String str = "";
        for (int i = 0; i < a.length; i++) {
            str += FormatterNumberM.formatNumber(a[i]);
            str += " ";
        }
        return str;
    }
    
    
    public static DynamicNumber[][] textToMatrix(String txt){
        int row_count =  0;
        int col_count = 0;
        String col_separator = " ";
        DynamicNumber[][] a = null;
        String[] rows = txt.trim().split("\\r?\\n");
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i].trim();
            if (row.indexOf(",") != -1) col_separator = ",";
            else if (row.indexOf("\t") != -1) col_separator = "\t";
            String[] cols = row.split(col_separator);
            if (cols.length > 0 && col_count == 0) col_count = cols.length;
            if (col_count != cols.length || col_count == 0) continue;
            row_count++;
        }
        if (row_count > 0 && col_count > 0){
            a = new DynamicNumber[row_count][col_count];
            for (int i = 0; i < row_count; i++) {
                String row = rows[i].trim();
                String[] cols = row.split(col_separator);
                if (cols.length != col_count) continue;
                for (int j = 0; j < col_count; j++) {
                    String[] t = cols[j].split("/");
                    if (t.length == 1) a[i][j] = new DynamicNumber(Double.parseDouble(t[0]));
                    else if (t.length == 2) a[i][j] = new DynamicNumber(Integer.parseInt(t[0])).div(new DynamicNumber(Integer.parseInt(t[1])));
                }
            }
        }
        return a;
    }
}
