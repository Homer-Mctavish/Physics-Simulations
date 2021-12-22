// performs many random walks of N steps
import java.io.*;

public class RandomWalkApp {
    public static void main(String[] args)
        throws FileNotFoundException{
	RandomWalk walk = new RandomWalk();
	walk.initialization();
	trials(walk, walk.Ntrials);
	walk.results();
    }

    public static void trials(RandomWalk walk, int Ntrials)
	throws FileNotFoundException{	
	for (int j=0; j< Ntrials; j++){
	    int position = walk.singleWalk(); //performs a signle walk ending at position
	    walk.updateHistogram(position);
	}
    }
}