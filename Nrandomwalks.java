import java.io.*;

public class Nrandomwalks{
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File ("NrandomWalks.txt"));
        int trials = 500000;
        int N = 20;
        double[] Xaccum = new double[N+1];
        double[] Xsquaredaccum = new double[N+1];
        double p = .7;
        for (int i = 0; i< trials; i++){
            int position = 0;
            for (int x = 0; x < N; x++){
                double rando = Math.random();
                if (rando <= p){
                    position--;
                } else{
                    position++;
                }
                Xaccum[x+1] += position;
                Xsquaredaccum[x+1] += Math.pow(position, 2);
        }
    }
        for(int g=0; g<=N; g++){
            double dummy=1.0*Xaccum[g]/trials;
            double dummy2=1.0*Xsquaredaccum[g]/trials-dummy*dummy;
            output.printf("%-6d %-10.5f %-10.5f \n",g,dummy, dummy2);
        }
    
    }
}