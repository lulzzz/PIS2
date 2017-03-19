/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shahmaliyev.simulation.pis2;

import com.shahmaliyev.simulation.SimulationParameters;

/**
 *
 * @author mamed.shahmaliyev
 */
public class SimulationParametersPIS2 extends SimulationParameters{
    public double lyambda; //intensity parameter of incoming poisson thread
    public double mu1; //exponential service time intensity of primary customer with service only and without stock withdrawal
    public double mu2; //exponential service time intensity of primary customer with service and stock withdrawal
    public double sigma1; //probability of service only customer
    public double sigma2; //=1-sigma1 probability of service and stock withdrawal customer
    public double gamma; //exponential time intensity of product perishing
    public double tao; //impatientce intensity
    public int N; //maximum size of orbit, -1 for infinity
    public int S; //maximum level of stock
    public int s; //stock threshold level for ordering S-s
    public double v; //exponential time intensity parameter for order delivery, V(n) depends on n, value for each n should be defined   
    
    @Override
    public String toString(){
        return "λ = "+lyambda+", μ1 = "+mu1+", μ2 = "+mu2+", σ1 = "+sigma1+", σ2 = "+sigma2+", τ = "+tao+", s = "+s+", S = "+S+", N = "+N+", γ = "+gamma+", ν = "+v;
    }
}
