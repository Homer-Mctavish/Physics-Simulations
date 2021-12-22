// Monte Carlo simulation of the Ising model
import java.io.*;
import java.util.*;

public class MCIsingApp {
    public static void main(String[] args)
        throws FileNotFoundException{
	MCIsing ising = new MCIsing();
	ising.initialization();
	boolean valid = true;
	while(valid){
	    int x = ising.randomCoord();// x coord of the spin we are proposing to flip
	    int y = ising.randomCoord();// y coord of the spin we are proposing to flip
	    double dE=ising.energyDiff(x,y);// energy difference between current configuration and new one if update is accepted
	    boolean yes = ising.decision(x,y,dE); //true if update is accepted
	    if (yes == true) {ising.update(x,y);} // update M and flip spin only if accepted
	    ising.updateCounters(); // update counters and abs(M) ALWAYS
	    //write on file after system has thermalized
	    if((ising.Z % ising.Wsteps) == 0&& ising.Z>1){
		ising.results();
	    }
	}
    }
}