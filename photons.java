import java.io.*;
import java.util.*;

public class photons {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Photons.txt"));
        Scanner sc = new Scanner(new File("photoninput.txt"));
        double R = returnValue(sc);
        double C = returnValue(sc);
        double Q0 = returnValue(sc);
        double L = returnValue(sc);
        double theta = returnValue(sc);
        double t = 0;
        double Q = Math.pow((1/2)*Q0, 2);
        double timestep = .01;
        double f1 =1/(L*C);
        double f2 = (R/(2*L))*(R/(2*L));
        double dw = Math.sqrt(f1-f2);
        double tau = R*C;
        double IR = Q0*(-1/tau)*Math.pow(Math.E, (-t/tau));
        double VR = IR*R;
        double VC = Q0/C;
        double VS = R*C*(VC/t)+VC; 
        for(int i = 0; i < 150; i++){  
            dw = Math.sqrt(f1-f2);
                double w = ((Math.sqrt((4*L/C)-(Math.pow(R, 2))))/(2*L));
                Q += Math.pow(Math.E, -R*t/2*L)*(Q0*Math.cos(theta)+Q0*Math.sin(theta)*t);
                IR = Math.pow(Math.E, (-t*R/L));
                VR = VR + IR*R;
                VS = VS + (VC+VR);
                theta = theta+dw;
                t += timestep;
                output.printf(" %3f %3f %3f \n", t, Q, IR);
        }

    }

    public static double returnValue(Scanner sc){
        double dummy = sc.nextDouble();
        sc.nextLine();
        return dummy;
    }

}
 