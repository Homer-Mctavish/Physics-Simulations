//Monte Carlo simulations of Ising model using spin flip algorithm
import java.io.*;
import java.util.*;

public class MCIsing {

    int L;         // lattice size
    double JT;     // ratio interaction temperature J/T	
    double M,AbsM;         // magnetization of a specific configuration and average of |M|
    int spin[][];  // stores the value of the spin
    double Z=0.0;     //counter(partition function)
    double Wsteps; //number of steps after which we write on file
    double MCstep=0.0;
    //constructor
    public MCIsing()
	throws FileNotFoundException{
    }

    
    //initialization
    public void initialization()
	throws FileNotFoundException{
	PrintWriter pw = new PrintWriter (new FileOutputStream(new File("Magnetization.txt"),false));
        Scanner input = new Scanner(new File("parameters.txt"));
	L = (int)returnValue(input);
	JT = returnValue(input);
	int order=(int)returnValue(input); // 0=disordered, 1=ordered
	Wsteps=returnValue(input); 
	spin = new int[L][L];
	initSpinCnf(order);
    }

    public void initSpinCnf(int order)
	throws FileNotFoundException{
	if(order==1){ 
	    for(int i=0; i<L;i++){
		for(int j=0;j<L;j++){
			spin[i][j]=1;
		}
	    }
	    M=L*L;
	}
	else{
	    for(int i=0; i<L;i++){
		for(int j=0;j<L;j++){
		    if(Math.random()<0.5){
			spin[i][j]=1;
			M=M+1;
		    }
		    else{
			spin[i][j]=-1;
			M=M-1;
		    }
		}
	    }
	}
	//do not initialize if do thermalization
	//	AbsM=Math.abs(M);
	//Z=1.0;//counter initialized to 1 since we include the initial configuration in the calculation of |M|
	MCstep=0.0;
	//	results();
    }

    public int randomCoord(){
        int i = (int)(Math.random()*L); 
	return i;
    }

    public  double energyDiff(int i, int j){
	int nnr = i+1 ; int nnl = i-1 ; // right and left nn neighbors
	int nnu = j+1 ; int nnd = j-1 ; // up and down nn neighbors
	//check for PBC below
	if(nnr==L){nnr=0;}
	if(nnl==-1){nnl=L-1;}
	if(nnu==L){nnu=0;}
	if(nnd==-1){nnd=L-1;}
	//calculation of the energy change if spin is flipped
	double dE=2.0*spin[i][j]*(spin[nnr][j]+spin[nnl][j]+spin[i][nnu]+spin[i][nnd]);
	return dE;
    }
    
    public boolean decision(int i, int j, double dE){
	boolean yes = false;
	double R = Math.exp(-JT*dE);
	if(R>1.0 || Math.random()<=R){
	    yes = true ; 
	}
	return yes;
}

    public void update(int i, int j){
	M=M-2.0*spin[i][j];
	spin[i][j]=-spin[i][j];
    }
    
    public void updateCounters(){
	MCstep++;
	if(MCstep>L*L*10){
	    Z=Z+1.0;
	    AbsM=AbsM+Math.abs(M);}
    }


    public void results()
	throws FileNotFoundException{
	    try{
		PrintWriter pw = new PrintWriter (new FileOutputStream(new File("Magnetization.txt"),true));
		pw.printf("%-10.7f\n",AbsM/(L*L*Z));
		pw.close();}
	    catch(IOException ioe){
		System.err.println("IOException: " + ioe.getMessage());
	    }
    }



    public double returnValue(Scanner input){
        double dummy=input.nextDouble();
	input.nextLine();
        return dummy;
    }

}
