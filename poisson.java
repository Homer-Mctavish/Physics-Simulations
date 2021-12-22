import java.io.*;

public class poisson {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("poisson1.txt"));
            int N = 10000;
            double p = .001;
            int counter=0;
            int Ntrials = 50000;
            int[] hist = new int[Ntrials];
            double[] po = new double[Ntrials];
            for ( int n = 1; n < Ntrials; n++){
                counter=0;
            for (int i =1; i <= N; i++){
                if(Math.random() < p){
                    counter++;
                }
            }
            hist[counter]++;
        }
        for (int y = 0; y < 25; y++){
            double pois = Math.pow((.001*10000), y)/fac(y)*Math.pow(Math.E, (-(.001*10000)));
            po[y]=pois;  
        }
        for (int k = 0; k < Ntrials; k++){
            output.printf("%3d %3d %3f \n", k, hist[k], po[k]);
        }

    }

    public static long fac(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}