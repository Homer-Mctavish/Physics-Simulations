import java.io.*;
import java.util.*;

public class RCApp {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Voltagese.txt"));
        Scanner sc = new Scanner(new File("inputs.txt"));
        double R = returnValue(sc);
        double C = returnValue(sc);
        double Q0 = returnValue(sc);
        double t = 0;
        double tau = R*C;
        double IR = Q0*(-1/tau)*Math.pow(Math.E, (-t/tau));
        double VR = IR*R;
        double VC = Q0/C;
        double VS = R*C*(VC/t)+VC;
        
        for(int i = 0; i < 150; i++){
            //discharge of capacitor
            IR = Q0*(-1/tau)*Math.pow(Math.E, (-t/tau));
            VR = VR + IR*R;
            VC = VC + Q0/C;
            VS = VS + (VC+VR);
            t ++;
            output.printf(" %3f %3f \n", t, VR);

        }

    }

    public static double returnValue(Scanner sc){
        double dummy = sc.nextDouble();
        sc.nextLine();
        return dummy;
    }

}
 