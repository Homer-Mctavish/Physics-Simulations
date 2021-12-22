/*
THis program generates an output file with one column of random numbers in the range [0,1) 
and one column integer random numbers in the range [1,100]
*/

import java.io.*;
import java.util.*;

public class WriteOnFile3{
    public static void main(String[] args)
	throws FileNotFoundException{
	PrintStream output = new PrintStream(new File ("RandomNumbers4.txt"));
	for (int i=0;i<100;i++){
	    Random r = new Random();//creates a new Random object
	    double x = r.nextDouble();//calls method nextDouble to generate a random number of double precision between 0 (inclusive) and 1 (exclusive)
	    int a = r.nextInt(100)+1;//calls method nextInt to generate an integer random number between 0(inclusive) and 100(exclusive)
	    output.printf("%-20.4f %3d \n",x,a);//% gets replaced by corresponding variable f for floating-point number, 20 total number of space fields; see tables online or on manuals for more examples
	}
    }
}