/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import static com.shahmaliyev.utilsM.UtilsM.gausMethod;

/**
 *
 * @author mamed.shahmaliyev
 */
public class SteadyState {
    public static DynamicNumber[] findSteadyStateFromBalanceEquations(DynamicNumber[][] transition_matrix,boolean log){
        //solving Ax=b
        if (transition_matrix.length != transition_matrix[0].length) return null;
        DynamicNumber[][] a = new DynamicNumber[transition_matrix.length][transition_matrix.length+1];
        //transpose matrix
        for (int i = 0; i < transition_matrix.length; i++){
            for (int j = i; j < transition_matrix[i].length; j++){
                DynamicNumber tmp = transition_matrix[i][j];
                transition_matrix[i][j] = transition_matrix[j][i];
                transition_matrix[j][i] = tmp;
                a[j][i]=transition_matrix[j][i];
                a[i][j]=transition_matrix[i][j];
            }
        }
        //add b column vector with 0s
        for (int i = 0; i < a.length; i++) {
            a[i][a[i].length-1]=DynamicNumber.ZERO();
        }
        //replace last row with 1s
        for (int i = 0; i < a[a.length-1].length; i++) {
            a[a.length-1][i]=DynamicNumber.ONE();
        }    
        DynamicNumber[] x = gausMethod(a,log);
        return x;
    }
    public static DynamicNumber[][] transitionRateToTransitionProbabilityMatrix(DynamicNumber[][] transition_matrix){
        if (transition_matrix.length != transition_matrix[0].length){
            return null;
        }
        for (int i = 0; i < transition_matrix.length; i++) {
            DynamicNumber row_sum = DynamicNumber.ZERO();
            for (int j = 0; j < transition_matrix[i].length; j++) {
                if (i != j) row_sum = row_sum.add(transition_matrix[i][j]);
            }
            for (int j = 0; j < transition_matrix[i].length; j++) {
                if (i != j) transition_matrix[i][j] = transition_matrix[i][j].div(row_sum);
            }
        }
        return transition_matrix;
    }
}
