/* Stores parameters for use across simulation */
/* A completely static class.  */

public class Parameters {

	// simulation parameters
	public static double time = 0;										// start time of simulation
	public static final double dt = 0.1;								// time step of simulation
	public static final double dxy = 0.015;								// lattice spacing
	public static final double threshold = Math.pow(10, -12);			// lower bound for a class to exist
		
	// epidemiological parameters
	public static final double birthDeath = 9.1 * Math.pow(10, -5);		// in births per individual per day, 1/30 years = 0.000091
	public static final double initialIProportion = 0.0015;				// as proportion of population
	public static final int initialIPosition = 1;						// as index
	public static final double initialRProportion = 0.35; 				// as proportion of population
	public static final int initialRPosition = -1;						// as index	
	public static final double beta = 0.3;								// in contacts per individual per day
	public static final double nu = 0.2;								// in recoveries per individual per day
		
	// phenotype parameters
	public static final double mu = 1.0; 								// in mutations per individual per day
	public static final boolean mutation2D = false;						// mutate in one or two dimensions
	public static final double riskByDistance = 0.1;					// one unit of distance increases transmissability by this amount	
		
}

