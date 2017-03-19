/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JLabel;

/**
 *
 * @author mamed.shahmaliyev
 */
public class GausCalculator {
    public static DynamicNumber[] gausMethod(DynamicNumber[][] a,boolean log){
        if (a.length != a[0].length-1){
            return null;
        }
        int m = a.length;
        int n = m + 1;
        
        int total_length = m;
        int progress = 0;
        int last_percent = 0;
        if (log) UtilsM.printToConsole("Gaus method progress is 0%");
        
        for (int j = 0; j < n-1; j++){
            if (log) {
                progress++;
                if (UtilsM.progressLabel != null) UtilsM.progressLabel.setText("Gaus method progress: "+new BigDecimal(new BigInteger(progress * 100+""),total_length).doubleValue());
                int aa = progress * 100/total_length % 10;
                int bb = progress * 100/total_length;
                if (aa == 0 && bb != last_percent){
                    last_percent = bb;
                    UtilsM.printToConsole(UtilsM.dateToStr()+": Gaus method progress is "+bb+"%");
                }       
            }
            for (int i = j; i < m; i++){
                if (!a[i][j].equals(0)){
                    if (!a[i][j].equals(1) && !a[i][j].equals(-1) ){
                        DynamicNumber d = a[i][j];
                        for (int k = j; k < n; k++){
                            a[i][k] =  a[i][k].div(d);
                        }
                    }
                    if (i != j){
                        for (int k = j; k < n; k++){
                            DynamicNumber tmp = a[j][k];
                            a[j][k] = a[i][k];
                            a[i][k] = tmp;
                        }
                    }
                    for (int k = i+1; k < m; k++){
                        DynamicNumber d = a[k][j];
                        for (int t = j; t < n; t++){
                            a[k][t] = a[k][t].sub(a[i][t].mult(d));
                        }
                    }
                    break;
                }
            }
        }
        DynamicNumber[] x = new DynamicNumber[m];
        for (int j = n-2; j>=0; j--){
            x[j] = a[j][n-1];
            for (int k = j+1; k < n-1; k++){
                x[j] = x[j].sub(a[j][k].mult(x[k]));
            }
        }

        return x;
    }
}
