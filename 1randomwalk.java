import java.io.*;

public class randomwalk{
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File ("1randomWalk.txt"));
        int N = 20;
        double p = .5;
        int position = 0;
        int time = 0;
            for (int x = 0; x < N; x++){
                double rando = Math.random();
                if (rando <= p){
                    position--;
                } else{
                    position++;
                }
                time++;
                output.printf("%3d %3d \n", time, position)
        }
    }
}