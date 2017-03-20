/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.util.Random;

/**
 *
 * @author mamed.shahmaliyev
 */
public class RandomGenerator {
    public static double randomGenerateExponential(double rate){
        Random r = new Random();
        return java.lang.Math.log(1.0-r.nextDouble()) / rate * -1.0;
    }
    public static boolean randomGenerateBernoulli(double p){
        Random r = new Random();
        return r.nextDouble() <= p;
    }
}
