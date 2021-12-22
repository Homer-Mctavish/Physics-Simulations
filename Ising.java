import java.io.*;
import java.util.concurrent.ThreadLocalRandom;


public class Ising{
  public static void main(String[] args) throws FileNotFoundException{
    PrintStream output = new PrintStream(new File ("ising.txt"));
    int L = 10;
    int s[][] = new int [L][L];
    double de = 0;
    double JoverT = 1;
    double E = 0;
    double M = 0;
    double Eavg = E;
    double Mavg = Math.abs(M);
    int Z = 1;
    for (int d =0; d<L; d++){
      for (int g = 0; g<L; g++){
        double rand = Math.random();
        if  (rand < 1){
          s[d][g] = 1;
          M += s[d][g];
        } else{
          s[d][g] = -1;
          M += s[d][g];
        }
      }
    }
   E = Energy(s, L, JoverT);
    Mavg = Math.abs(M);
    Eavg = E/L*L;
    for(int y = 0; y < 1800; y++){
      double Eini = E;
      int i =ThreadLocalRandom.current().nextInt(0, L);
      int j =ThreadLocalRandom.current().nextInt(0, L);
      int change = -1*s[i][j];
      s[i][j] = change;
      double Ehypo = Energy(s, L, JoverT);
      de = Ehypo-Eini;
      double R = Math.pow(Math.E, (de*-JoverT));
      double rand = Math.random();
      if( R < 1 || rand <= R){
      M = M+2*change;
        E = Ehypo;
        Mavg = Math.abs(M);
        Eavg = E/L*L;
        Z++;
      } else{
        E = Eini;
        Mavg = Math.abs(M);
        Eavg = E/L*L;
        Z++;
      }
      output.printf("%-10.7f \n",Mavg/(L*L*Z));
    }
  }
 
  public static double Energy(int[][] s, int L, double JoverT) {
    double E = 0;
    for(int k = 0; k<L;k++){
      for(int h = 0; h<L; h++){
        int kk = 0;
        int hh = 0;
        if(k<L-1){
          kk=k+1;
    } else{
      kk=0;
    }
    if(h<L-1){
      hh = h+1;
    } else{
      hh=0;
    }
    E = E + s[k][h]*(s[kk][h]+s[k][hh]);
  }
}
E = E*(-JoverT);
return E;
}
}
