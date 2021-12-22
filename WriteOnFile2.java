/*
This program writes random numbers on a file
 */

import java.io.*;
import java.util.*;

public class WriteOnFile2{
    public static void main(String[] args)
	throws FileNotFoundException{
	PrintStream output = new PrintStream(new File ("RandomNumbers.txt"));
	PrintStream output2 = new PrintStream(new File ("RandomNumbers2.txt"));
	PrintStream output3 = new PrintStream(new File ("RandomNumbers3.txt"));
	    //PrintStream is a Java class. Here we have created a new object of type PrintStream
	    //tied to an output file
	for (int i=0;i<100;i++){
	     double x = Math.random(); //this uses the random method from Math class
	    output.println(x); 
	    //alternatively you can use the Random object
	    Random r = new Random();//creates a new Random object
	    x = r.nextDouble();//calls method nextDouble to generate a random number of double precision between 0 (inclusive) and 1 (exclusive)
	    output2.println(x);
	    int a = r.nextInt(100)+1;//calls method nextInt to generate an integer random number between 0(inclusive) and 100(exclusive)
	    output3.println(a);
	}
    }
}