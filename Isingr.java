//Monte Carlo simulations of Ising model using spin flip algorithm
import java.io.*;
import java.util.*;

public class Isingr {
    int L;         // lattice size
    double JT;     // ratio interaction temperature J/T	
    double M,AbsM;         // magnetization of a specific configuration and average of |M|
    int spin[][];  // stores the value of the spin
    double Z=0.0;     //counter(partition function)
    double Wsteps; //number of steps after which we write on file
    double MCstep=0.0;

    public static void main (String[] args)throws FileNotFoundException{
        int L;         // lattice size
        double JT;     // ratio interaction temperature J/T	
        double M,AbsM;         // magnetization of a specific configuration and average of |M|
        int spin[][];  // stores the value of the spin
        double Z=0.0;     //counter(partition function)
        double Wsteps; //number of steps after which we write on file
        double MCstep=0.0;
        PrintWriter pw = new PrintWriter (new FileOutputStream(new File("Magnetization.txt"),false));
        Scanner input = new Scanner(new File("parameters.txt"));
	L = (int)returnValue(input);
	JT = returnValue(input);
	int order=(int)returnValue(input); // 0=disordered, 1=ordered
	Wsteps=returnValue(input); 
	spin = new int[L][L];
	initSpinCnf(order);
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

    boolean valid = true;
	while(valid){
	    int x = randomCoord();// x coord of the spin we are proposing to flip
	    int y = randomCoord();// y coord of the spin we are proposing to flip
	    double dE=energyDiff(x,y);// energy difference between current configuration and new one if update is accepted
	    boolean yes = decision(x,y,dE); //true if update is accepted
	    if (yes == true) {update(x,y);} // update M and flip spin only if accepted
	    updateCounters(); // update counters and abs(M) ALWAYS
	    //write on file after system has thermalized
	    if((ising.Z % ising.Wsteps) == 0&& ising.Z>1){
		ising.results();

    MCstep=0.0;

    pw.printf("%-10.7f\n",AbsM/(L*L*Z));
    pw.close();
    }
    
    public int randomCoord(){
        int i = (int)(Math.random()*L); 
	return i;
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


    public  double energyDiff(int i, int j){
        int nnr = i+1 ; int nnl = i-1 ; 
        int nnu = j+1 ; int nnd = j-1 ; 
        if(nnr==L){nnr=0;}
        if(nnl==-1){nnl=L-1;}
        if(nnu==L){nnu=0;}
        if(nnd==-1){nnd=L-1;}
        double dE=2.0*spin[i][j]*(spin[nnr][j]+spin[nnl][j]+spin[i][nnu]+spin[i][nnd]);
        return dE;
        }

    public static double returnValue(Scanner input){
        double dummy=input.nextDouble();
	input.nextLine();
        return dummy;
    }
}
    