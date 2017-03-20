/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import org.apache.commons.math3.fraction.BigFraction;

/**
 *
 * @author mamed.shahmaliyev
 */
public class UtilsM {
    
    public static JTextArea output;
    public static JLabel progressLabel;//To Display Progress
    
    public static String getLineSeparator(){
        return System.getProperty("line.separator");
    }
    
    //-----------------------------------------------Working with Date and Time-----------------------------------------------------//
    public static String dateToStr(String format,Date date){
        return (new SimpleDateFormat (format)).format(date);
    }
    public static String dateToStr(String format){
        return dateToStr(format, new Date());
    }
    public static String dateToStr(Date d){
        return dateToStr("yyyy.MM.dd hh:mm:sss.S", d);
    }
    public static String dateToStr(){
        return dateToStr("yyyy.MM.dd hh:mm:sss.S",new Date());
    }
    
    
    
    //-------------------------------------------------Working with FileSystem------------------------------------------------------//
    public static void writeToFile(String filename,String data,boolean append) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File(filename);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), append);
            bw = new BufferedWriter(fw);
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }    
    
    //---------------------------------------------------print output--------------------------------------------------//
    public static void printToConsole(String s){
        if (output == null) System.out.println(s);
        else printToConsole(s,output);
    }
    public static void printToConsole(String s, JTextArea output){
        if (output != null) {
            output.append(s+UtilsM.getLineSeparator());
            textAreaAutoScrol(output);
        }
        else System.out.println(s);
    }
    
    
    public static void textAreaAutoScrol(JTextArea t){
        DefaultCaret caret = (DefaultCaret)t.getCaret(); 
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  
    }
    
    public static int getAvailableProcessors(){
        int a = Runtime.getRuntime().availableProcessors();
        if (a>1) a = a - 1;
        return a;
    }
    public static void stopExecutor(ExecutorService executor) {
        if (executor == null) return;
        try {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }
    
    
    //------------------------------------Wrapper Methods-----------------------------------------------------/
    
    //--------------------------------------Math--------------------------------------------------------//
    public static BigInteger factorial(int n) {return MathM.factorial(n);} 
    
    //-------------------------------------Random Generators---------------------------------------------------//
    public static double randomGenerateExponential(double rate){return RandomGenerator.randomGenerateExponential(rate);}
    public static boolean randomGenerateBernulli(double p){return RandomGenerator.randomGenerateBernoulli(p);}
    
    //--------------------------------------Gaus Method---------------------------------------------------------//
    public static DynamicNumber[] gausMethod(DynamicNumber[][] a,boolean log){return GaussianCalculator.gausMethod(a, log);}
    
    //--------------------------------------Steady State---------------------------------------------------------//
    public static DynamicNumber[][] transitionRateToTransitionProbabilityMatrix(DynamicNumber[][] transition_matrix){return SteadyState.transitionRateToTransitionProbabilityMatrix(transition_matrix);}
    public static DynamicNumber[] findSteadyStateFromBalanceEquations(DynamicNumber[][] transition_matrix,boolean log){return SteadyState.findSteadyStateFromBalanceEquations(transition_matrix, log);}
   
    //--------------------------------------Norms----------------------------------------------------------------------------//
    public static DynamicNumber euclideanNorm2(DynamicNumber[] v1, DynamicNumber[] v2){return NormsCalculator.euclideanNorm2(v1, v2);}
    public static DynamicNumber euclideanNorm(DynamicNumber[] v1, DynamicNumber[] v2){return NormsCalculator.euclideanNorm(v1, v2);}
    public static DynamicNumber[] maxABS(DynamicNumber[] v1, DynamicNumber[] v2){return NormsCalculator.maxABS(v1, v2);}
    
    
    //---------------------------------------------Matrix&String----------------------------------------------//   
    public static String matrixToString(BigFraction[][] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(DynamicNumber[][] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(BigDecimal[][] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(double[][] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(BigFraction[] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(DynamicNumber[] a,boolean formatted){return FormatterMatrixM.matrixToString(a,formatted);}
    public static String matrixToString(DynamicNumber[] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(BigDecimal[] a){return FormatterMatrixM.matrixToString(a);}
    public static String matrixToString(double[] a){return FormatterMatrixM.matrixToString(a);}
    public static DynamicNumber[][] textToMatrix(String txt){return FormatterMatrixM.textToMatrix(txt);}
    
    
    //---------------------------------------------Number&String----------------------------------------------//
    public static String formatNumber(double a){return FormatterNumberM.formatNumber(a);}
    public static BigFraction strToBigFraction(String str){return FormatterNumberM.strToBigFraction(str);}
    public static double strToDouble(String str){return FormatterNumberM.strToDouble(str);}
    public static Integer strToInteger(String str){return FormatterNumberM.strToInteger(str);}
    
}

