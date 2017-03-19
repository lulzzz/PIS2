# PIS2
This project contains Source Codes for Perishable Inventory Systems (PIS) with Impatient Customers, Single Server, Finite/Infinite Buffer, and (S,s) policy to calculate its characteristics and steady state probabilities with innovative approximate Consolidation formulas (<small><i>by Prof. Dr. Agassi Melikov, agassi.melikov@gmail.com</i></small>) and exact Gaus formula.

Project Structure contains 2 main packages.

1) <b>com.shahmaliyev.utilsM</b>    
  1.1) classes FormatterMatrixM, FormatterMatrixM are used to convert text data to matrix and vice versa.  
  1.2) class GausCalculator to solve square linear equations using classical Gaus formula.  
  1.3) class RandomGenerator to generate exponentially and bernulli distributed random variables.  
  1.4) class SteadyState to calculate Steady State probabilities from balance equations.  
  1.5) class MathM contains factorial function to calculate factorial for large integers.  
  1.6) class UtilsM contains different practical functions (working with dates, console, formats etc.).  
  1.7) class NormsCalculator contains functions to calculate different norms (Euclidean Norm, Cosine Similarity, Jaccard Similariy etc.).  
  1.8) class DynamicNumber is used in whole project for number operations it has three modes: double, bigdecimal and bigfraction, we choose required mode depending on speed, division accuracy etc, for example if we need to calculate Gaus formula for matrix with numerical instability(that is dividing very small decimal numbers) then we could use bigdecimal or bigfraction mode in order to prevent precision loss in intermediary divisions.  
  
2) <b>com.shahmaliyev.simulation.pis2</b> - package that contains class associated with PIS.  
  2.1) SimulationCharacteristicsPIS2 - contains functions for calculation characteristics and steady state probabilities of given PIS.  
  2.2) SimulationParametersPIS2 - defines parameters of given PIS and is passed to SimulationCharacteristicsPIS2 class.  
  2.3) Example usage(in Java programming language):   
  
      SimulationParametersPIS2 simulationParameters = new SimulationParametersPIS2();         
      simulationParameters.N=150;  
      simulationParameters.S=50;  
      simulationParameters.gamma=2;  
      simulationParameters.lyambda=30;  
      simulationParameters.mu1=6;  
      simulationParameters.mu2=3;  
      simulationParameters.sigma1=0.7;  
      simulationParameters.sigma2=0.3;  
      simulationParameters.s=20;  
      simulationParameters.tao=0.5;  
      simulationParameters.v=1;  
      
      SimulationCharacteristicsPIS2 sc = new SimulationCharacteristicsPIS2();  
      sc.calculateConsolidation(sp, false);  
      sc.calculateGaus(sp, false);  
      
      System.out.println(sc.Sav);  
      System.out.println(sc.Gav);  
      System.out.println(sc.RR);  
      System.out.println(sc.Lav);  
      System.out.println(sc.PL);  
      System.out.println(UtilsM.matrixToString(sc.p_m_n));     
      
     
 PS: Please also download Apache Commons Math library in order to use BigFraction class. Version used in project is 3.6.1 located in lib folder. Link for latest version: http://commons.apache.org/proper/commons-math/download_math.cgi
  

