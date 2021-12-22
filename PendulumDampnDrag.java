import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
public class PendulumDampnDrag {
    public static void main(String[] args)
    throws FileNotFoundException{
        Scanner sc = new Scanner (new File("parameters1.txt"));
        PrintStream output = new PrintStream(new File ("OutputFileDrive.txt"));
        double L = returnValue(sc);
        double angle_ini = returnValue(sc);
        double angle_vel = returnValue(sc);
        double c = returnValue(sc);
        double A = returnValue(sc);
        double w0 = returnValue(sc);
        double g = 9.81;
        double alpha = 0;
        double timestep =.01;
        double energy = 0;
        double t = 0;
        for (int i = 0; i < 150;i++){
            double avm = angle_vel + .5*alpha*timestep;
            double anglmid =  angle_ini + .5*angle_vel*timestep;
            double alphamid =  (-((g)/L)*Math.sin(anglmid))-(c*avm+A*Math.sin(w0*(t+.5*timestep)));
            angle_ini += avm * timestep;
            angle_vel += alphamid * timestep;
            t = t + timestep;
            alpha = (-((g)/L)*Math.sin(angle_ini))-(c*angle_vel+A*Math.sin(w0*(t)));
            energy = .5*Math.pow(angle_vel, 2)*Math.pow(L, 2)+g*L*(1-Math.cos(angle_ini));
            output.printf(" %3f\t %3f\t %3f\t %3f\t \n", t,angle_ini,angle_vel, energy);
        }
    }

    public static double returnValue(Scanner input){
        double dummy=input.nextDouble();
        input.nextLine();
        return dummy;
        }
}