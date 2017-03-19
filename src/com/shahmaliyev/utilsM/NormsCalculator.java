/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

/**
 *
 * @author mamed.shahmaliyev
 */
public class NormsCalculator {
    public static DynamicNumber euclideanNorm2(DynamicNumber[] v1, DynamicNumber[] v2){
        DynamicNumber euclidean_norm = DynamicNumber.ZERO();
        for (int i = 0; i < v1.length; i++) {
            euclidean_norm = euclidean_norm.add((v1[i].sub(v2[i])).pow(2));
        }
        euclidean_norm = euclidean_norm.pow(0.5).div(v1.length);
        return euclidean_norm;
    }
    public static DynamicNumber euclideanNorm(DynamicNumber[] v1, DynamicNumber[] v2){
        DynamicNumber euclidean_norm = DynamicNumber.ZERO();
        for (int i = 0; i < v1.length; i++) {
            euclidean_norm = euclidean_norm.add((v1[i].sub(v2[i])).pow(2));
        }
        euclidean_norm = euclidean_norm.pow(0.5);
        return euclidean_norm;
    }
    public static DynamicNumber cosineSimilarity(DynamicNumber[] v1, DynamicNumber[] v2){
        DynamicNumber sum_numerator = DynamicNumber.ZERO();
        DynamicNumber sum_denominator1 = DynamicNumber.ZERO();
        DynamicNumber sum_denominator2 = DynamicNumber.ZERO();
        for (int i = 0; i < v1.length; i++) {
            sum_numerator = sum_numerator.add(v1[i].mult(v2[i]));
            sum_denominator1 = sum_denominator1.add(v1[i].mult(v1[i]));
            sum_denominator2 = sum_denominator2.add(v2[i].mult(v2[i]));
        }
        return sum_numerator.div(  sum_denominator1.pow(0.5).mult(sum_denominator2.pow(0.5))  );
    }
    public static DynamicNumber jaccardSimilarity(DynamicNumber[] v1, DynamicNumber[] v2){
        DynamicNumber sum_numerator = DynamicNumber.ZERO();
        DynamicNumber sum_denominator = DynamicNumber.ZERO();
        for (int i = 0; i < v1.length; i++) {
            sum_numerator = sum_numerator.add(DynamicNumber.min(v1[i], v2[i]));
            sum_denominator = sum_denominator.add(DynamicNumber.max(v1[i], v2[i]));
        }
        return sum_numerator.div(  sum_denominator  );
    }
    public static DynamicNumber[] maxABS(DynamicNumber[] v1, DynamicNumber[] v2){
        DynamicNumber[] max_abs = new DynamicNumber[2];
        max_abs[0]=DynamicNumber.ZERO();
        max_abs[1]=DynamicNumber.ZERO();
        for (int i = 0; i < v1.length; i++) {
            if ( v1[i].sub(v2[i]).compareTo(max_abs[0]) == 1 ) {
                max_abs[0] = v1[i].sub(v2[i]).abs();
                max_abs[1] = new DynamicNumber(i);
            }
        }
        return max_abs;
    }
}
