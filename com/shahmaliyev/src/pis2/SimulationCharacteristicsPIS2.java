/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.simulation.pis2;

import com.shahmaliyev.simulation.SimulationCharacteristics;
import com.shahmaliyev.utilsM.DynamicNumber;
import com.shahmaliyev.utilsM.UtilsM;
/**
 *
 * @author mamed.shahmaliyev
 */
public class SimulationCharacteristicsPIS2 extends SimulationCharacteristics{
    public double Sav;//mean value of stock
    public double Lav;//mean size of queue
    public double Gav;//product perishing rate
    public double PL;//customer loss rate (probability of customer loss)
    public double PL_1;//customer loss rate (probability of customer loss)
    public double PL_2;//customer loss rate (probability of customer loss)
    public double RR;//stock replenishment(reorder) rate
    
    public DynamicNumber[] p_m_n; //fixed probability vector, or steady state probabilities,p(m,n) = p[m*(N+1)+n], where m is stock level, n is queue length
       
    public DynamicNumber[][] getTransitionMatrix(SimulationParametersPIS2 sp,boolean log){
        int total_length = (sp.S+1)*(sp.N+1)*(sp.S+1)*(sp.N+1);
        int progress = 0;
        int last_percent = 0;
        if (log) UtilsM.printToConsole("Gaus generating Q matrix progress is 0%");
        DynamicNumber[][] t = new DynamicNumber[(sp.S+1)*(sp.N+1)][(sp.S+1)*(sp.N+1)];
        for (int m1 = 0; m1 <= sp.S; m1++) {
            for (int n1 = 0; n1 <= sp.N; n1++) {
                for (int m2 = 0; m2 <= sp.S; m2++) {
                    for (int n2 = 0; n2 <= sp.N; n2++) {
                        if (log) {
                            progress++;
                            UtilsM.progressLabel.setText("Gaus Q Matrix progress: "+(double)progress * 100/(double)total_length);
                            int aa = progress * 100/total_length % 10;
                            int bb = progress * 100/total_length;
                            if (aa == 0 && bb != last_percent){
                                last_percent = bb;
                                UtilsM.printToConsole(UtilsM.dateToStr()+": Gaus generating Q matrix progress is "+bb+"%");
                            }       
                        }
                        int i = m1*(sp.N+1)+n1;
                        int j = m2*(sp.N+1)+n2;                        
                        if (m1>sp.s){
                            if (m2==m1 && n2==n1+1) t[i][j] = DynamicNumber.NEW(sp.lyambda);
                            else if (m2==m1 && n2==n1-1) t[i][j] = DynamicNumber.NEW(sp.mu1*sp.sigma1);
                            else if (m2==m1-1 && n2==n1-1) t[i][j] = DynamicNumber.NEW(sp.mu2*sp.sigma2);
                            else if (m2==m1-1 && n2==0 && n1==0) t[i][j] = DynamicNumber.NEW(m1*sp.gamma);
                            else if (m2==m1-1 && n2==n1 && n1>0) t[i][j] = DynamicNumber.NEW((m1-1)*sp.gamma);
                            else t[i][j] = DynamicNumber.ZERO();
                        } else if (m1>0 && m1<=sp.s){
                            if (m2==m1 && n2==n1+1) t[i][j] = DynamicNumber.NEW(sp.lyambda);
                            else if (m2==m1 && n2==n1-1) t[i][j] = DynamicNumber.NEW(sp.mu1*sp.sigma1);
                            else if (m2==m1-1 && n2==n1-1) t[i][j] = DynamicNumber.NEW(sp.mu2*sp.sigma2);
                            else if (m2==m1-1 && n2==0 && n1==0) t[i][j] = DynamicNumber.NEW(m1*sp.gamma);
                            else if (m2==m1-1 && n2==n1 && n1>0) t[i][j] = DynamicNumber.NEW((m1-1)*sp.gamma);
                            else if (m2==m1+(sp.S-sp.s) && n2==n1) t[i][j] = DynamicNumber.NEW(sp.v);
                            else t[i][j] = DynamicNumber.ZERO();
                        } else if (m1==0){
                            if (m2==0 && n2==n1+1) t[i][j] = DynamicNumber.NEW(sp.lyambda);
                            else if (m2==0 && n2==n1-1) t[i][j] = DynamicNumber.NEW(n1*sp.tao);
                            else if (m2==0+sp.S-sp.s && n2==n1) t[i][j] = DynamicNumber.NEW(sp.v);
                            else t[i][j] = DynamicNumber.ZERO();
                        }
                    }
                }
            }
        }
        for (int i = 0; i < t.length; i++) {
            DynamicNumber sum = DynamicNumber.ZERO();
            for (int j = 0; j < t[i].length; j++) {
                if (i != j) sum = sum.add(t[i][j]);
            }
            t[i][i]=sum.mult(-1);
        }
        return t;
    }
    public void calculateNumericalResults(SimulationParametersPIS2 sp, boolean log, DynamicNumber[] p_m_n){
        DynamicNumber sum = DynamicNumber.ZERO();
        for (int i = 0; i < p_m_n.length; i++) {
            sum = sum.add(p_m_n[i]);
        }
        if (log) System.out.println("Steady State sum test: "+sum.toString());
        
        DynamicNumber Sav1 = DynamicNumber.ZERO();
        for (int m = 1; m <= sp.S; m++) {
            for (int n = 0; n <= sp.N; n++) {
                Sav1 = Sav1.add(p_m_n[m*(sp.N+1)+n].mult(m));
            }
        }
        Sav = Sav1.doubleValue();
        
        DynamicNumber Gav1 = DynamicNumber.ZERO();
        for (int m = 1; m <= sp.S; m++) {
            Gav1 = Gav1.add(p_m_n[m*(sp.N+1)+0].mult(m));
            for (int n = 1; n <= sp.N; n++) {
                Gav1 = Gav1.add(p_m_n[m*(sp.N+1)+n].mult(m-1));
            }
        }
        Gav1 = Gav1.mult(sp.gamma);
        Gav = Gav1.doubleValue();
        
        DynamicNumber RR1 = DynamicNumber.ZERO();
        for (int n = 1; n <= sp.N; n++) {
            RR1 = RR1.add(p_m_n[(sp.s+1)*(sp.N+1)+n]);
        }
        RR1=RR1.mult(sp.mu2*sp.sigma2+sp.s*sp.gamma);
        RR1 = RR1.add(p_m_n[(sp.s+1)*(sp.N+1)+0].mult(sp.s+1).mult(sp.gamma));
        RR = RR1.doubleValue();
        
        DynamicNumber PL1 = DynamicNumber.ZERO();
        for (int m = 0; m <= sp.S; m++) {
            PL1 = PL1.add(p_m_n[m*(sp.N+1)+sp.N]);
        }
        PL_1 = PL1.doubleValue();
        
        DynamicNumber PL2 = DynamicNumber.ZERO();
        double b = sp.lyambda/sp.tao;
        for (int n = 1; n <= sp.N-1; n++) {
            PL2 = PL2.add(p_m_n[0*(sp.N+1)+n].mult( n/(n+b) ));
        }
        PL_2 = PL2.doubleValue();
        
        PL = PL1.add(PL2).doubleValue();
        
        DynamicNumber Lav1 = DynamicNumber.ZERO();
        for (int n = 1; n <= sp.N; n++) {
            DynamicNumber tmp = DynamicNumber.ZERO();
            for (int m = 0; m <= sp.S; m++) {
                tmp = tmp.add(p_m_n[m*(sp.N+1)+n]);
            }
            Lav1 = Lav1.add(tmp.mult(n));
        }
        Lav = Lav1.doubleValue();
    }
    public void calculateGaus(SimulationParametersPIS2 sp, boolean log){
        DynamicNumber[][] t = getTransitionMatrix(sp,log);
        //if (log) System.out.println("Transition matrix is: "+myutils.matrixToString(t));
        p_m_n = UtilsM.findSteadyStateFromBalanceEquations(t,log);
        if (log) System.out.println("Steady state is: "+ UtilsM.matrixToString(p_m_n));
        calculateNumericalResults(sp, log, p_m_n);        
    }
    public void calculateConsolidation(SimulationParametersPIS2 sp, boolean log){
        if (sp.N != -1) calculateFiniteQueue(sp,log);
        else calculateInfiniteQueue(sp,log);
    }
    
    private DynamicNumber[] calculateCommon(SimulationParametersPIS2 sp, boolean log, DynamicNumber[] ro_n){
        //Λi - lyamba_i
        DynamicNumber[] L_i = new DynamicNumber[sp.S+1];
        L_i[0] = DynamicNumber.ZERO();
        for (int i = 1; i <= sp.S; i++) {
            L_i[i]=new DynamicNumber(i).mult(ro_n[0]).mult(new DynamicNumber(sp.gamma)).add(  DynamicNumber.ONE().sub(ro_n[0]).mult(new DynamicNumber(sp.mu2*sp.sigma2).add(new DynamicNumber(sp.gamma*(i-1))))     );
        }
        if (log) System.out.println("L_i"+UtilsM.matrixToString(L_i));
        
        DynamicNumber[] alfa_m = new DynamicNumber[sp.S+1];
        for (int m = 0; m <= sp.s; m++) {
            alfa_m[m]=DynamicNumber.ONE();
            for (int i = m+1; i <= sp.s+1; i++) {
                alfa_m[m]=alfa_m[m].mult(  L_i[i].div(new DynamicNumber(sp.v).add(L_i[i-1]))  );
            }
        }
        
        DynamicNumber[] betta_m = new DynamicNumber[sp.S+1];
        for (int m = sp.s+1; m <= sp.S-sp.s; m++) {
            betta_m[m]=L_i[sp.s+1].div(L_i[m]);
        }
        
        DynamicNumber[] ksi_m = new DynamicNumber[sp.S+1];
        for (int m = sp.S-sp.s+1; m <= sp.S; m++) {
            DynamicNumber s=DynamicNumber.ZERO();
            for (int i = m-sp.S+sp.s; i <= sp.s; i++) {
                s=s.add(alfa_m[i]);
            }
            ksi_m[m]=new DynamicNumber(sp.v).div(L_i[m]).mult(s);
        }
        
        DynamicNumber[] pi_m = new DynamicNumber[sp.S+1];
        pi_m[sp.s+1]=DynamicNumber.ZERO();
        for (int m = 0; m <= sp.s; m++) {
            pi_m[sp.s+1]=pi_m[sp.s+1].add(alfa_m[m]);
        }
        for (int m = sp.s+1; m <= sp.S-sp.s; m++) {
            pi_m[sp.s+1]=pi_m[sp.s+1].add(betta_m[m]);
        }
        for (int m = sp.S-sp.s+1; m <= sp.S; m++) {
            pi_m[sp.s+1]=pi_m[sp.s+1].add(ksi_m[m]);
        }
        pi_m[sp.s+1]=DynamicNumber.ONE().div(pi_m[sp.s+1]);
        
        for (int m = 0; m <= sp.s; m++) {
            if (m == sp.s+1) continue;
            pi_m[m]=alfa_m[m].mult(pi_m[sp.s+1]);
        }
        for (int m = sp.s+1; m <= sp.S-sp.s; m++) {
            if (m == sp.s+1) continue;
            pi_m[m]=betta_m[m].mult(pi_m[sp.s+1]);
        }
        for (int m = sp.S-sp.s+1; m <= sp.S; m++) {
            if (m == sp.s+1) continue;
            pi_m[m]=ksi_m[m].mult(pi_m[sp.s+1]);
        }
        
        if (log){
            DynamicNumber temp = DynamicNumber.ZERO();
            for (int i = 0; i < pi_m.length; i++) {
                temp=temp.add(pi_m[i]);
            }
            System.out.println("PI : "+UtilsM.matrixToString(pi_m));
            System.out.println("PI sum test: "+temp);
        }      
        return pi_m;
    }
    private void calculateFiniteQueue(SimulationParametersPIS2 sp, boolean log){
        //ro_n is for m=1,...,S
        DynamicNumber[] ro_n = new DynamicNumber[sp.N+1];
        DynamicNumber a = new DynamicNumber(sp.lyambda,DynamicNumber.modeBigDecimal);
        a = a.div(sp.mu1*sp.sigma1);
        for (int i = 0; i < ro_n.length; i++) {   
            DynamicNumber t = a.pow(i).mult(DynamicNumber.ONE(DynamicNumber.modeBigDecimal).sub(a)).div(DynamicNumber.ONE(DynamicNumber.modeBigDecimal).sub(a.pow(sp.N+1)));
            ro_n[i] = t.changeModeTo(DynamicNumber.modeDefault);
        }        
        if (log) System.out.println("ro_n"+UtilsM.matrixToString(ro_n));
        
        //ro_0_n is for m=0
        DynamicNumber[] ro_0_n = new DynamicNumber[sp.N+1];
        DynamicNumber b = new DynamicNumber(sp.lyambda,DynamicNumber.modeBigDecimal);
        b = b.div(sp.tao);
        DynamicNumber sum = DynamicNumber.ZERO(DynamicNumber.modeBigDecimal);
        for (int i = 0; i <= sp.N; i++) {
            sum = sum.add(b.pow(i).div(UtilsM.factorial(i)));
        }
        for (int i = 0; i <= sp.N; i++) {
            DynamicNumber t = b.pow(i).div(UtilsM.factorial(i)).div(sum);
            ro_0_n[i] = t.changeModeTo(DynamicNumber.modeDefault);
        }
        if (log) System.out.println("ro_0_n"+UtilsM.matrixToString(ro_0_n));
        
        DynamicNumber[] pi_m = calculateCommon(sp, log, ro_n);
        
        p_m_n = new DynamicNumber[(sp.S+1)*(sp.N+1)];
        for (int m = 0; m <= sp.S; m++) {
            for (int n = 0; n <= sp.N; n++) {
                p_m_n[m*(sp.N+1)+n]=(m>0)?ro_n[n].mult(pi_m[m]):ro_0_n[n].mult(pi_m[m]);
            }
        }
        if (log) System.out.println("Steady state is: "+UtilsM.matrixToString(p_m_n));
        
        calculateNumericalResults(sp, log, p_m_n);
        
//        DynamicNumber Sav1 = DynamicNumber.ZERO();
//        for (int m = 1; m <= sp.S; m++) {
//            Sav1=Sav1.add(pi_m[m].mult(new DynamicNumber(m)));
//        }
//        Sav = Sav1.doubleValue();
//        
//        DynamicNumber Gav1 = DynamicNumber.ZERO();
//        for (int m = 1; m <= sp.S; m++) {
//            Gav1= Gav1.add(pi_m[m].mult(     new DynamicNumber(m).mult(ro_n[0]).add(  new DynamicNumber(m-1).mult(DynamicNumber.ONE().sub(ro_n[0]))     )    ));
//        }
//        Gav1 = Gav1.mult(new DynamicNumber(sp.gamma));
//        Gav = Gav1.doubleValue();
//                
//        DynamicNumber RR1 = pi_m[sp.s+1].mult(   new DynamicNumber((sp.s+1)*sp.gamma).mult(ro_n[0]).add(   DynamicNumber.ONE().sub(ro_n[0]).mult(new DynamicNumber(sp.s*sp.gamma+sp.mu2*sp.sigma2))  )    );
//        RR = RR1.doubleValue();
//        
//        DynamicNumber PL1 = ro_n[sp.N].mult(DynamicNumber.ONE().sub(pi_m[0]));
//        DynamicNumber temp = DynamicNumber.ZERO();
//        for (int n = 1; n <= sp.N-1; n++) {
//            temp = temp.add (     ro_0_n[n].mult(new DynamicNumber(n).div(b.add(new DynamicNumber(n))))   );
//        }
//        PL1 = PL1.add(   pi_m[0].mult(ro_0_n[sp.N].add(temp))  );
//        PL = PL1.doubleValue();
//        
//        DynamicNumber Lav1 = DynamicNumber.ZERO();
//        for (int n = 1; n <= sp.N; n++) {
//            Lav1 = Lav1.add(ro_0_n[n].mult(n));
//        }
//        Lav1=Lav1.mult(pi_m[0]);
//        temp = DynamicNumber.ZERO();
//        for (int n = 1; n <= sp.N; n++) {
//            temp = temp.add( ro_n[n].mult(n) );
//        }
//        Lav1=Lav1.add(   temp.mult(DynamicNumber.ONE().sub(pi_m[0]))  );       
//        Lav=Lav1.doubleValue();           
    }
    private void calculateInfiniteQueue(SimulationParametersPIS2 sp, boolean log){        
        DynamicNumber[] ro_n = new DynamicNumber[1];
        DynamicNumber a = new DynamicNumber(sp.lyambda).div(new DynamicNumber(sp.mu1*sp.sigma1));
        for (int n = 0; n < ro_n.length; n++) {
            ro_n[n]=DynamicNumber.ONE().sub(a).mult(a.pow(n));
        }        
        if (log) System.out.println("ro_n"+UtilsM.matrixToString(ro_n));
                
        DynamicNumber[] pi_m = calculateCommon(sp, log, ro_n);
        
        DynamicNumber Sav1 = DynamicNumber.ZERO();
        for (int m = 1; m <= sp.S; m++) {
            Sav1=Sav1.add(pi_m[m].mult(new DynamicNumber(m)));
        }
        Sav = Sav1.doubleValue();
        
        DynamicNumber Gav1 = DynamicNumber.ZERO();
        for (int m = 1; m <= sp.S; m++) {
            Gav1= Gav1.add(pi_m[m].mult(     new DynamicNumber(m).mult(ro_n[0]).add(  new DynamicNumber(m-1).mult(DynamicNumber.ONE().sub(ro_n[0]))     )    ));
        }
        Gav1 = Gav1.mult(sp.gamma);
        Gav = Gav1.doubleValue();
                
        DynamicNumber RR1 = pi_m[sp.s+1].mult(   new DynamicNumber((sp.s+1)*sp.gamma).mult(ro_n[0]).add(   DynamicNumber.ONE().sub(ro_n[0]).mult(new DynamicNumber(sp.s*sp.gamma+sp.mu2*sp.sigma2))  )    );
        RR = RR1.doubleValue();
        
        DynamicNumber b = new DynamicNumber(sp.lyambda,DynamicNumber.modeBigDecimal);
        b = b.div(sp.tao);
        
        DynamicNumber PL1 = pi_m[0].div(new DynamicNumber(Math.pow(Math.E,b.doubleValue())));
        DynamicNumber sum = DynamicNumber.ZERO(DynamicNumber.modeBigDecimal);
        boolean limit_found = false;
        DynamicNumber[] last_values = new DynamicNumber[5];
        DynamicNumber error = new DynamicNumber(0.000000001,DynamicNumber.modeBigDecimal);
        int step = 0;
        int i = -1;
        while(!limit_found && step<300) {
            if (i == 4) i = 0;
            else i++;
            sum = sum.add(b.pow(step).div(UtilsM.factorial(step)).mult(new DynamicNumber(step,DynamicNumber.modeBigDecimal).div(b.add(step)))   );
            last_values[i]=sum;
            if (step>5){
                boolean tmp = true;
                for (int j = 1; j < 5; j++) {
                    DynamicNumber e = last_values[j].sub(last_values[j-1]);
                    e = e.abs();
                    if ((last_values[j].sub(last_values[j-1])).abs().compareTo(error) == 1) {
                        tmp=false;
                        break;
                    }
                }
                if (tmp) limit_found=true;
            } 
            step++;
        }
        if (log) System.out.println("sum limit: "+sum.doubleValue());
        PL1 = PL1.mult(sum.changeModeTo(DynamicNumber.modeDefault));
        PL = PL1.doubleValue();
        
        b.changeModeTo(DynamicNumber.modeDefault);
        DynamicNumber Lav1 = pi_m[0].mult(b);
        Lav1 = Lav1.add( DynamicNumber.ONE().sub(pi_m[0]).mult(a).div(DynamicNumber.ONE().sub(a)) )    ;       
        Lav=Lav1.doubleValue();  
    }
}
