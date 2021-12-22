// performs many random walks of N steps
import java.io.*;
import java.util.*;

public class RandomWalk {
    int Nsteps;   //number of steps                                               
    double p;     //probability	
    int Ntrials;  //number of random walks performed
    int xAccum[]; // accumulates the value of position as a function of time step
    int xSquaredAccum[] ; // accumulates the square of position as a function of time step
    int hist[]; //histogram for final dispalcement, once walk is over 

    //constructor
    public RandomWalk()
	throws FileNotFoundException{
    }

    
    //initialization
    public void initialization()
	throws FileNotFoundException{
        Scanner input = new Scanner(new File("parameters.txt"));
	Nsteps = (int)returnValue(input);
	p = returnValue(input);
	Ntrials = (int)returnValue(input);
	xAccum = new int[Nsteps+1]; //xAccum[0]=0, before first step
	xSquaredAccum = new int[Nsteps+1]; //xSquaredAccum[0]=0 , before first step
	hist = new int[Nsteps+1]; //histogram: only even positions will be reachied if Nsteps is even
    }



    public int singleWalk(){
	int position = 0;
	for (int i=0; i< Nsteps; i++){
	    position=update(position);
	    xAccum[i+1] += position;
	    xSquaredAccum[i+1] += position*position;
	}
	return position;
    }

    public  int update(int position){
        if(Math.random()<p){
	    position++;
	}
	else{
	    position--;
	}
	return position;
    }

    public void results()
	throws FileNotFoundException{
	PrintStream output1 = new PrintStream(new File ("output.txt"));        
	PrintStream output2 = new PrintStream(new File ("histogram.txt"));        
	for (int i=0; i<= Nsteps; i++){
	    double dummy=1.0*xAccum[i]/Ntrials;
	    double dummy2=1.0*xSquaredAccum[i]/Ntrials-dummy*dummy;
	    output1.printf("%-6d %-10.5f %-10.5f \n",i,dummy, dummy2);
	}
	double average=0.0;
	double prob=0.0;
	for (int i=0; i<=Nsteps; i++){
	    output2.printf("%-6d %-8.4f \n",2*i-Nsteps,1.0*hist[i]/Ntrials);
	    average=average+1.0*(2*i-Nsteps)*hist[i]/(Ntrials);
	    prob=prob+1.0*hist[i]/(Ntrials);
	}
	System.out.println("average=   "+average+"   prob=  "+prob);//just to check that I am doing things right
    }



    public void updateHistogram(int position){
	hist[(position+Nsteps)/2]+=1; //if Nsteps is even, final position is always even
	//position is mapped to a positive index number
	//most of the histogram will not be updated because those positins have low probability to be reached
	//you could use ArrayList
    }



    public double returnValue(Scanner input){
        double dummy=input.nextDouble();
	input.nextLine();
        return dummy;
    }

}