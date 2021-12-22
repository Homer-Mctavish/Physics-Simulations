import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gasex{
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Gas1.txt"));
        int time = 0;
        int N = 14400;
            int nleft = N/2;
            double meansum = 0;
            double Nsteps = 500000;
            for (int j =0; j < Nsteps;j++){
                double rando = ThreadLocalRandom.current().nextInt(1, N + 1);;
                if (rando < nleft) {
                    nleft--;
                } else {
                    nleft++;
                } 
                time++;
                meansum += (Math.pow(nleft-N/2, 2))/Nsteps;
            }
            System.out.println(meansum);
    }
}