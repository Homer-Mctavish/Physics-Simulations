import java.io.*;
import java.util.*;

public class RLCApp {
    public static void main(String[] args) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File ("Voltages.txt"));
        Scanner sc = new Scanner(new File("components.txt"));
        Scanner scint = new Scanner(new File("counts.txt"));
                int resistorCount = (int)returnValue(scint);        // number of resistors
                int inductorCount = (int)returnValue(scint);        // number of inductors
                int capacitorCount = (int)returnValue(scint);       // number of capacitors
                double[] resistors = new double[resistorCount];     // resistors
                double[] inductors = new double[inductorCount];     // inductors
                double[] capacitors = new double[capacitorCount];   // capacitors
        
                // read in values from stdin
                for (int i = 0; i < resistorCount; i++)
                    resistors[i] = returnValue(sc);
                for (int i = 0; i < inductorCount; i++)
                    inductors[i] = returnValue(sc);
                for (int i = 0; i < capacitorCount; i++)
                    capacitors[i] = returnValue(sc);
        
                double frequency = 50;           // angular frequency of voltage (w)
        
                Complex impedance = new Complex(0, 0);
        
                for (int i = 0; i < resistorCount; i++) {
                    Complex resistance = new Complex(resistors[i], 0);
                    impedance = impedance.plus(resistance);
                    output.printf(" %3f \n", impedance);
                }
        
                // complex impedance of an inductor is iwL
                for (int i = 0; i < inductorCount; i++) {
                    Complex reactance = new Complex(0, frequency * inductors[i]);
                    impedance = impedance.plus(reactance);
                    output.printf(" %3f \n", impedance);
                }
        
                // complex impedance of a capacitor is 1/(iwC) 
                for (int i = 0; i < capacitorCount; i++) {
                    Complex reactance = new Complex(0, -1.0/(frequency * capacitors[i]));
                    impedance = impedance.plus(reactance);
                    output.printf(" %3f \n", impedance);
                }
        
        
                double realImpedance = impedance.abs();
                output.printf(" %3f \n", realImpedance);
        
                // relative phase = angle at which voltage leads current
                double phase = impedance.phase();
                System.out.println("phase = " + phase);
        
    }

    public static double returnValue(Scanner sc){
        double dummy = sc.nextDouble();
        sc.nextLine();
        return dummy;
    }

}
 