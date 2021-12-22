import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gas{
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Gasp14400.txt"));
        int[] N = {14400};
        int time = 0;
        for (int y = 0; y<N.length;y++){
            int nleft = N[y];
            int ntotal = N[y];
            for (int j =0; j < N[y];j++){
                int rando = ThreadLocalRandom.current().nextInt(1, ntotal + 1);
                if (rando < nleft){
                    nleft--;
                } else {
                    nleft++;
                }
                time++;
                output.printf("%3d %3d, \n", time, nleft);
            }

        }
    }
}