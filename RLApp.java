
import java.io.*;
import java.util.*;

public class RLApp {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Voltages2.txt"));
        Scanner sc = new Scanner(new File("inputs.txt"));
        double R = returnValue(sc);
        double C = returnValue(sc);
        double Qmax = returnValue(sc);
        double L = returnValue(sc);
        double theta = returnValue(sc);
        double t = 0;
        double damp = (Math.sqrt((4*L/C)-R*R)/2*L);
        double Q = Math.pow((1/2)*Qmax, 2);
        double timestep = .01;
        for(int i = 0; i < 150; i++){
            //discharge of capacitor
            double avm = damp + .5*Math.pow(Math.E, -R*t/2*L)*timestep;
            double anglmid =  Qmax + .5*damp*timestep;
                damp = (Math.sqrt((4L/C)-R*R))/2*L;
                Q=Qmax*Math.pow(Math.E, -R*t/2*L)*Math.cos(damp*t-theta);
                t = t + timestep;
                output.printf(" %3f %3f \n", t, Q);

        }

    }

    public static double returnValue(Scanner sc){
        double dummy = sc.nextDouble();
        sc.nextLine();
        return dummy;
    }

}
