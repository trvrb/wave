/* Stores parameters for use across simulation */
/* A completely static class.  */

public class Parameters {

	// simulation parameters
	public static double time = 0;									// start time of simulation
	public static final double dt = 0.1;							// time step of simulation
	public static final double dxy = 0.01;							// lattice spacing
		
	// epidemiological parameters
	public static final double birthRate = 0.000091;				// in births per individual per day, 1/30 years = 0.000091
	public static final double initialIProportion = 0.01;			// as proportion of population
	public static final int initialIPosition = 1;					// as index
	public static final double initialRProportion = 0.5; 			// as proportion of population
	public static final int initialRPosition = 0;					// as index	
	public static final double beta = 0.3;							// in contacts per individual per day
	public static final double nu = 0.2;							// in recoveries per individual per day
		
	// phenotype parameters
	public static final double muPhenotype = 0.005; 				// in mutations per individual per day
	public static final double riskByDistance = 0.1;				// one unit of distance increases transmissability by this amount	
		
}