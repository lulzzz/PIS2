/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.utilsM;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.apache.commons.math3.fraction.BigFraction;

/**
 *
 * @author mamed.shahmaliyev
 */
public final class DynamicNumber{
    private double numberDouble;
    
    public static RoundingMode rm = RoundingMode.HALF_UP;
    public static int sc = 40;
    private BigDecimal numberBigDecimal;
    private BigFraction numberBigFraction;
    
    private byte mode=1;
    public static byte modeDouble=1;
    public static byte modeBigDecimal=2;
    public static byte modeBigFraction=3;
    public static byte modeDefault=1;
    
    public static DynamicNumber min(DynamicNumber a,DynamicNumber b){
        if(a.compareTo(b) == 1) return b;
        else return a;
    }
    public static DynamicNumber max(DynamicNumber a,DynamicNumber b){
        if(a.compareTo(b) == 1) return a;
        else return b;
    }

    public DynamicNumber(double a,byte m) {mode=m; if(mode==modeDouble)numberDouble=a;else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a);else if(mode==modeBigFraction)numberBigFraction=new BigFraction(a);}
    public DynamicNumber(BigDecimal a,byte m) {mode=m; if(mode==modeDouble)numberDouble=a.doubleValue();else if(mode==modeBigDecimal)numberBigDecimal=a;else if(mode==modeBigFraction)numberBigFraction=new BigFraction(a.doubleValue());}
    public DynamicNumber(BigFraction a,byte m) {mode=m; if(mode==modeDouble)numberDouble=a.doubleValue();else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a.doubleValue());else if(mode==modeBigFraction)numberBigFraction=a;}
    public DynamicNumber(String a,byte m) {mode=m; if(mode==modeDouble)numberDouble=Double.parseDouble(a);else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a);else if(mode==modeBigFraction)numberBigFraction=new BigFraction(Double.parseDouble(a));}
    public DynamicNumber(int a,byte m) {mode=m; if(mode==modeDouble)numberDouble=(double)a;else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a);else if(mode==modeBigFraction)numberBigFraction=new BigFraction(a);}
    public DynamicNumber(long a,byte m) {mode=m; if(mode==modeDouble)numberDouble=(double)a;else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a);else if(mode==modeBigFraction)numberBigFraction=new BigFraction(a);}
    public DynamicNumber(BigInteger a,byte m) {mode=m; if(mode==modeDouble)numberDouble=a.doubleValue();else if(mode==modeBigDecimal)numberBigDecimal=new BigDecimal(a);else if(mode==modeBigFraction)numberBigFraction=new BigFraction(a);}
    public DynamicNumber(DynamicNumber a,byte m) {mode=m; if(mode==modeDouble)numberDouble=a.doubleValue();else if(mode==modeBigDecimal)numberBigDecimal=a.bigDecimalValue();else if(mode==modeBigFraction)numberBigFraction=a.bigFractionValue();}

    public DynamicNumber(double a) { this(a,modeDefault);}
    public DynamicNumber(BigDecimal a) { this(a,modeDefault);}
    public DynamicNumber(BigFraction a) { this(a,modeDefault);}
    public DynamicNumber(String a) { this(a,modeDefault);}
    public DynamicNumber(int a) { this(a,modeDefault);}
    public DynamicNumber(long a) { this(a,modeDefault);}
    public DynamicNumber(BigInteger a) { this(a,modeDefault);}
    public DynamicNumber(DynamicNumber a) { this(a,modeDefault);}
    
    public static DynamicNumber NEW(double a){return new DynamicNumber(a);}
    public static DynamicNumber NEW(BigDecimal a){return new DynamicNumber(a);}
    public static DynamicNumber NEW(BigFraction a){return new DynamicNumber(a);}
    public static DynamicNumber NEW(String a){return new DynamicNumber(a);}
    public static DynamicNumber NEW(int a){return new DynamicNumber(a);}
    public static DynamicNumber NEW(long a){return new DynamicNumber(a);}
    
    public static DynamicNumber ONE(){
        return new DynamicNumber(1);
    }
    public static DynamicNumber ONE(byte m){
        return new DynamicNumber(1,m);
    }
    public static DynamicNumber ZERO(){
        return new DynamicNumber(0);
    }
    public static DynamicNumber ZERO(byte m){
        return new DynamicNumber(0,m);
    }
    
    public DynamicNumber changeModeTo(byte m){
        if (mode == modeDouble) return new DynamicNumber(numberDouble,m);
        else if (mode == modeBigDecimal) return new DynamicNumber(numberBigDecimal,m);
        else if (mode == modeBigFraction) return new DynamicNumber(numberBigFraction,m);
        return DynamicNumber.ZERO();
    }
    
    public DynamicNumber abs(){
        DynamicNumber t = new DynamicNumber(this,mode); 
        if (mode == modeDouble) t.numberDouble=Math.abs(t.numberDouble);
        else if (mode == modeBigDecimal) t.numberBigDecimal=t.numberBigDecimal.abs();
        else if (mode == modeBigFraction) t.numberBigFraction=t.numberBigFraction.abs();
        return t;
    }
    public int compareTo(DynamicNumber a){
        if (mode == modeDouble) return Double.valueOf(numberDouble).compareTo(a.doubleValue());
        else if (mode == modeBigDecimal) return numberBigDecimal.compareTo(a.bigDecimalValue());
        else if (mode == modeBigFraction) return numberBigFraction.compareTo(a.bigFractionValue());
        else return 0;
    }
    
    public boolean equals(DynamicNumber a){
        if (mode == modeDouble) return a.bigFractionValue().equals(new BigFraction(numberDouble));
        if (mode == modeBigDecimal) return a.bigDecimalValue().equals(numberBigDecimal);
        if (mode == modeBigFraction) return a.bigFractionValue().equals(numberBigFraction);
        else return false;
    }
    public boolean equals(double a){
        if (mode == modeDouble) return new BigFraction(a).equals(new BigFraction(numberDouble));
        if (mode == modeBigDecimal) return new BigDecimal(a).equals(numberBigDecimal);
        if (mode == modeBigFraction) return new BigFraction(a).equals(numberBigFraction);
        else return false;
    }
    public boolean equals(int a){
        if (mode == modeDouble) return new BigFraction(a).equals(new BigFraction(numberDouble));
        if (mode == modeBigDecimal) return new BigDecimal(a).equals(numberBigDecimal);
        if (mode == modeBigFraction) return new BigFraction(a).equals(numberBigFraction);
        else return false;
    }
    public boolean equals(long a){
        if (mode == modeDouble) return new BigFraction(a).equals(new BigFraction(numberDouble));
        if (mode == modeBigDecimal) return new BigDecimal(a).equals(numberBigDecimal);
        if (mode == modeBigFraction) return new BigFraction(a).equals(numberBigFraction);
        else return false;
    }
    public boolean equals(String a){
        if (mode == modeDouble) return new BigFraction(Double.parseDouble(a)).equals(new BigFraction(numberDouble));
        if (mode == modeBigDecimal) return new BigDecimal(a).equals(numberBigDecimal);
        if (mode == modeBigFraction) return new BigFraction(Double.parseDouble(a)).equals(numberBigFraction);
        else return false;
    }
    
    public long longValue(){if (mode == modeDouble) return ( Double.valueOf(numberDouble) ).intValue( );else if (mode == modeBigDecimal) return numberBigDecimal.longValue();else if (mode == modeBigFraction) return numberBigFraction.longValue();else return 0;}
    public int intValue(){if (mode == modeDouble) return ( Double.valueOf(numberDouble) ).intValue( );else if (mode == modeBigDecimal) return numberBigDecimal.intValue();else if (mode == modeBigFraction) return numberBigFraction.intValue();else return 0;}
    public double doubleValue(){
        if (mode == modeDouble) return numberDouble;
        else if (mode == modeBigDecimal) return numberBigDecimal.doubleValue();
        else if (mode == modeBigFraction) return numberBigFraction.doubleValue();
        else return 0.0;
    }
    public float floatValue(){ return (float)doubleValue();    }
    public BigDecimal bigDecimalValue(){if (mode == modeDouble) return new BigDecimal(numberDouble);else if (mode == modeBigDecimal) return numberBigDecimal;else if (mode == modeBigFraction) return new BigDecimal(numberBigFraction.doubleValue());else return BigDecimal.ZERO;}
    public BigFraction bigFractionValue(){if (mode == modeDouble) return new BigFraction(numberDouble);else if (mode == modeBigFraction) return numberBigFraction;else if (mode == modeBigDecimal) return new BigFraction(numberBigDecimal.doubleValue());else return BigFraction.ZERO;}
    
    @Override
    public String toString(){
        if (mode == modeDouble) return String.valueOf(numberDouble);
        else if (mode == modeBigFraction) return numberBigFraction.toString();
        else if (mode == modeBigDecimal) return numberBigDecimal.toString(); 
        else return "";
    }
    
    
    
    
    public DynamicNumber add(double a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(BigFraction a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(BigDecimal a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(String a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(int a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(long a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(BigInteger a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    public DynamicNumber add(DynamicNumber a){DynamicNumber t = new DynamicNumber(this,mode); a.changeModeTo(mode); if(mode == modeDouble)t.numberDouble=addDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=addBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=addBigFraction(a);return t;}
    
    
    
    public DynamicNumber sub(double a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(BigFraction a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(BigDecimal a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(String a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(int a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(long a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(BigInteger a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
    public DynamicNumber sub(DynamicNumber a){DynamicNumber t = new DynamicNumber(this,mode); a.changeModeTo(mode); if(mode == modeDouble) t.numberDouble=subtractDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=subtractBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=subtractBigFraction(a);return t;}
   
    
    public DynamicNumber mult(double a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(BigFraction a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(BigDecimal a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(String a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(int a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(long a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(BigInteger a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    public DynamicNumber mult(DynamicNumber a){DynamicNumber t = new DynamicNumber(this,mode); a.changeModeTo(mode); if(mode == modeDouble) t.numberDouble=multiplyDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=multiplyBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=multiplyBigFraction(a);return t;}
    
    
    
    public DynamicNumber div(double a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(BigFraction a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(BigDecimal a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(String a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(int a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(long a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(BigInteger a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    public DynamicNumber div(DynamicNumber a){DynamicNumber t = new DynamicNumber(this,mode); a.changeModeTo(mode); if(mode == modeDouble) t.numberDouble=divideDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=divideBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=divideBigFraction(a);return t;}
    
    
    public DynamicNumber pow(double a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    public DynamicNumber pow(BigFraction a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    public DynamicNumber pow(BigDecimal a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    public DynamicNumber pow(String a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    public DynamicNumber pow(int a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    public DynamicNumber pow(BigInteger a){DynamicNumber t = new DynamicNumber(this,mode); if(mode == modeDouble) t.numberDouble=powDouble(a);else if (mode == modeBigDecimal) t.numberBigDecimal=powBigDecimal(a);else if (mode == modeBigFraction) t.numberBigFraction=powBigFraction(a);return t;}
    
    
    
    private BigFraction addBigFraction(double a){return numberBigFraction.add(new BigFraction(a));}
    private BigFraction addBigFraction(BigFraction a){return numberBigFraction.add(a);}
    private BigFraction addBigFraction(BigDecimal a){return numberBigFraction.add(new BigFraction(a.doubleValue()));}
    private BigFraction addBigFraction(String a){return numberBigFraction.add(new BigFraction(Double.parseDouble(a)));}
    private BigFraction addBigFraction(int a){return numberBigFraction.add(new BigFraction(a));}
    private BigFraction addBigFraction(long a){return numberBigFraction.add(new BigFraction(a));}
    private BigFraction addBigFraction(BigInteger a){return numberBigFraction.add(new BigFraction(a));}
    private BigFraction addBigFraction(DynamicNumber a){return numberBigFraction.add(a.numberBigFraction);}
    
    private BigFraction subtractBigFraction(double a){return numberBigFraction.subtract(new BigFraction(a));}
    private BigFraction subtractBigFraction(BigFraction a){return numberBigFraction.subtract(a);}
    private BigFraction subtractBigFraction(BigDecimal a){return numberBigFraction.subtract(new BigFraction(a.doubleValue()));}
    private BigFraction subtractBigFraction(String a){return numberBigFraction.subtract(new BigFraction(Double.parseDouble(a)));}
    private BigFraction subtractBigFraction(int a){return numberBigFraction.subtract(new BigFraction(a));}
    private BigFraction subtractBigFraction(long a){return numberBigFraction.subtract(new BigFraction(a));}
    private BigFraction subtractBigFraction(BigInteger a){return numberBigFraction.subtract(new BigFraction(a));}
    private BigFraction subtractBigFraction(DynamicNumber a){return numberBigFraction.subtract(a.numberBigFraction);}
    
    private BigFraction multiplyBigFraction(double a){return numberBigFraction.multiply(new BigFraction(a));}
    private BigFraction multiplyBigFraction(BigFraction a){return numberBigFraction.multiply(a);}
    private BigFraction multiplyBigFraction(BigDecimal a){return numberBigFraction.multiply(new BigFraction(a.doubleValue()));}
    private BigFraction multiplyBigFraction(String a){return numberBigFraction.multiply(new BigFraction(Double.parseDouble(a)));}
    private BigFraction multiplyBigFraction(int a){return numberBigFraction.multiply(new BigFraction(a));}
    private BigFraction multiplyBigFraction(long a){return numberBigFraction.multiply(new BigFraction(a));}
    private BigFraction multiplyBigFraction(BigInteger a){return numberBigFraction.multiply(new BigFraction(a));}
    private BigFraction multiplyBigFraction(DynamicNumber a){return numberBigFraction.multiply(a.numberBigFraction);}
    
    private BigFraction divideBigFraction(double a){return numberBigFraction.divide(new BigFraction(a));}
    private BigFraction divideBigFraction(BigFraction a){return numberBigFraction.divide(a);}
    private BigFraction divideBigFraction(BigDecimal a){return numberBigFraction.divide(new BigFraction(a.doubleValue()));}
    private BigFraction divideBigFraction(String a){return numberBigFraction.divide(new BigFraction(Double.parseDouble(a)));}
    private BigFraction divideBigFraction(int a){return numberBigFraction.divide(new BigFraction(a));}
    private BigFraction divideBigFraction(long a){return numberBigFraction.divide(new BigFraction(a));}
    private BigFraction divideBigFraction(BigInteger a){return numberBigFraction.divide(new BigFraction(a));}
    private BigFraction divideBigFraction(DynamicNumber a){return numberBigFraction.divide(a.numberBigFraction);}
    
    private BigFraction powBigFraction(Double a){return new BigFraction(numberBigFraction.pow(a));}
    private BigFraction powBigFraction(BigFraction a){return new BigFraction(numberBigFraction.pow(a.doubleValue()));}
    private BigFraction powBigFraction(BigDecimal a){return new BigFraction(numberBigFraction.pow(a.doubleValue()));}
    private BigFraction powBigFraction(String a){return new BigFraction(numberBigFraction.pow(Double.parseDouble(a)));}
    private BigFraction powBigFraction(int a){return numberBigFraction.pow(a);}
    private BigFraction powBigFraction(BigInteger a){return numberBigFraction.pow(a);}
    
    private BigDecimal addBigDecimal(double a){return numberBigDecimal.add(new BigDecimal(a));}
    private BigDecimal addBigDecimal(BigDecimal a){return numberBigDecimal.add(a);}
    private BigDecimal addBigDecimal(BigFraction a){return numberBigDecimal.add(new BigDecimal(a.getNumerator()).divide(new BigDecimal(a.getDenominator()),sc,rm));}
    private BigDecimal addBigDecimal(String a){return numberBigDecimal.add(new BigDecimal(a));}
    private BigDecimal addBigDecimal(int a){return numberBigDecimal.add(new BigDecimal(a));}
    private BigDecimal addBigDecimal(long a){return numberBigDecimal.add(new BigDecimal(a));}
    private BigDecimal addBigDecimal(BigInteger a){return numberBigDecimal.add(new BigDecimal(a));}
    private BigDecimal addBigDecimal(DynamicNumber a){return numberBigDecimal.add(a.numberBigDecimal);}
    
    private BigDecimal subtractBigDecimal(double a){return numberBigDecimal.subtract(new BigDecimal(a));}
    private BigDecimal subtractBigDecimal(BigDecimal a){return numberBigDecimal.subtract(a);}
    private BigDecimal subtractBigDecimal(BigFraction a){return numberBigDecimal.subtract(new BigDecimal(a.getNumerator()).divide(new BigDecimal(a.getDenominator()),sc,rm));}
    private BigDecimal subtractBigDecimal(String a){return numberBigDecimal.subtract(new BigDecimal(a));}
    private BigDecimal subtractBigDecimal(int a){return numberBigDecimal.subtract(new BigDecimal(a));}
    private BigDecimal subtractBigDecimal(long a){return numberBigDecimal.subtract(new BigDecimal(a));}
    private BigDecimal subtractBigDecimal(BigInteger a){return numberBigDecimal.subtract(new BigDecimal(a));}
    private BigDecimal subtractBigDecimal(DynamicNumber a){return numberBigDecimal.subtract(a.numberBigDecimal);}
    
    private BigDecimal multiplyBigDecimal(double a){return numberBigDecimal.multiply(new BigDecimal(a));}
    private BigDecimal multiplyBigDecimal(BigDecimal a){return numberBigDecimal.multiply(a);}
    private BigDecimal multiplyBigDecimal(BigFraction a){return numberBigDecimal.multiply(new BigDecimal(a.getNumerator()).divide(new BigDecimal(a.getDenominator()),sc,rm));}
    private BigDecimal multiplyBigDecimal(String a){return numberBigDecimal.multiply(new BigDecimal(a));}
    private BigDecimal multiplyBigDecimal(int a){return numberBigDecimal.multiply(new BigDecimal(a));}
    private BigDecimal multiplyBigDecimal(long a){return numberBigDecimal.multiply(new BigDecimal(a));}
    private BigDecimal multiplyBigDecimal(BigInteger a){return numberBigDecimal.multiply(new BigDecimal(a));}
    private BigDecimal multiplyBigDecimal(DynamicNumber a){return numberBigDecimal.multiply(a.numberBigDecimal);}
    
    private BigDecimal divideBigDecimal(double a){return numberBigDecimal.divide(new BigDecimal(a),sc,rm);}
    private BigDecimal divideBigDecimal(BigDecimal a){return numberBigDecimal.divide(a,sc,rm);}
    private BigDecimal divideBigDecimal(BigFraction a){return numberBigDecimal.divide(new BigDecimal(a.getNumerator()).divide(new BigDecimal(a.getDenominator()),sc,rm),sc,rm);}
    private BigDecimal divideBigDecimal(String a){return numberBigDecimal.divide(new BigDecimal(a),sc,rm);}
    private BigDecimal divideBigDecimal(int a){return numberBigDecimal.divide(new BigDecimal(a),sc,rm);}
    private BigDecimal divideBigDecimal(long a){return numberBigDecimal.divide(new BigDecimal(a),sc,rm);}
    private BigDecimal divideBigDecimal(BigInteger a){return numberBigDecimal.divide(new BigDecimal(a),sc,rm);}
    private BigDecimal divideBigDecimal(DynamicNumber a){return numberBigDecimal.divide(a.numberBigDecimal,sc,rm);}
    
    private BigDecimal powBigDecimal(Double a){return new BigDecimal(Math.pow(numberBigDecimal.doubleValue(), a));}
    private BigDecimal powBigDecimal(BigDecimal a){return new BigDecimal(Math.pow(numberBigDecimal.doubleValue(), a.doubleValue()));}
    private BigDecimal powBigDecimal(BigFraction a){return new BigDecimal(Math.pow(numberBigDecimal.doubleValue(), a.doubleValue()));}
    private BigDecimal powBigDecimal(String a){return new BigDecimal(Math.pow(numberBigDecimal.doubleValue(), Double.parseDouble(a)));}
    private BigDecimal powBigDecimal(int a){return numberBigDecimal.pow(a);}
    private BigDecimal powBigDecimal(BigInteger a){return numberBigDecimal.pow(a.intValue());}
    
    private double addDouble(double a){return numberDouble+a;}
    private double addDouble(BigDecimal a){return numberDouble+a.doubleValue();}
    private double addDouble(BigFraction a){return numberDouble+a.doubleValue();}
    private double addDouble(String a){return numberDouble+Double.parseDouble(a);}
    private double addDouble(int a){return numberDouble+(double)a;}
    private double addDouble(long a){return numberDouble+(double)a;}
    private double addDouble(BigInteger a){return numberDouble+a.doubleValue();}
    private double addDouble(DynamicNumber a){return numberDouble+a.numberDouble;}
    
    private double subtractDouble(double a){return numberDouble-a;}
    private double subtractDouble(BigDecimal a){return numberDouble-a.doubleValue();}
    private double subtractDouble(BigFraction a){return numberDouble-a.doubleValue();}
    private double subtractDouble(String a){return numberDouble-Double.parseDouble(a);}
    private double subtractDouble(int a){return numberDouble-(double)a;}
    private double subtractDouble(long a){return numberDouble-(double)a;}
    private double subtractDouble(BigInteger a){return numberDouble-a.doubleValue();}
    private double subtractDouble(DynamicNumber a){return numberDouble-a.numberDouble;}
    
    private double multiplyDouble(double a){return numberDouble*a;}
    private double multiplyDouble(BigDecimal a){return numberDouble*a.doubleValue();}
    private double multiplyDouble(BigFraction a){return numberDouble*a.doubleValue();}
    private double multiplyDouble(String a){return numberDouble*Double.parseDouble(a);}
    private double multiplyDouble(int a){return numberDouble*(double)a;}
    private double multiplyDouble(long a){return numberDouble*(double)a;}
    private double multiplyDouble(BigInteger a){return numberDouble*a.doubleValue();}
    private double multiplyDouble(DynamicNumber a){return numberDouble*a.numberDouble;}
    
    private double divideDouble(double a){return numberDouble/a;}
    private double divideDouble(BigDecimal a){return numberDouble/a.doubleValue();}
    private double divideDouble(BigFraction a){return numberDouble/a.doubleValue();}
    private double divideDouble(String a){return numberDouble/Double.parseDouble(a);}
    private double divideDouble(int a){return numberDouble/(double)a;}
    private double divideDouble(long a){return numberDouble/(double)a;}
    private double divideDouble(BigInteger a){return numberDouble/a.doubleValue();}
    private double divideDouble(DynamicNumber a){return numberDouble/a.numberDouble;}
    
    private double powDouble(double a){return Math.pow(numberDouble, a);}
    private double powDouble(BigDecimal a){return Math.pow(numberDouble, a.doubleValue());}
    private double powDouble(BigFraction a){return Math.pow(numberDouble, a.doubleValue());}
    private double powDouble(String a){return Math.pow(numberDouble, Double.parseDouble(a));}
    private double powDouble(int a){return Math.pow(numberDouble, (double)a);}
    private double powDouble(long a){return Math.pow(numberDouble, (double)a);}
    private double powDouble(BigInteger a){return Math.pow(numberDouble, a.doubleValue());}
    private double powDouble(DynamicNumber a){return Math.pow(numberDouble, a.numberDouble);}
    
    public static BigInteger factorial(int n) {
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    } 
    
}
