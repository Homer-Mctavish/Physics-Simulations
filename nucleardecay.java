import java.io.*;


public class nucleardecay {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("nuclear10000.txt"));
            int N_ini = 10000;
            int N = N_ini;
            double p = .001;
            double percentage = .01;
            int timestep =0;
            int counter=0;
            while ( N > percentage*N_ini){
                counter=0;
            for (int i =1; i <= N;i++){
                if(Math.random() < p){
                    counter++;
                }
            }
            N=N-counter;
            timestep++;
            output.printf("%3d %3d \n", timestep, N);
        }

    }
}