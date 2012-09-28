/* Epidemiological forces.  Contains references to classes to update. */

public class Forces {

	private Susceptible susceptible;
	private Infecteds infecteds;
	private Recovereds recovereds;

    public Forces(Susceptible sus, Infecteds inf, Recovereds rec) {
    	susceptible = sus;
    	infecteds = inf;
    	recovereds = rec;
    }
    
    public void step() {
    
    	contact();
    	recovery();
    	pushStoredValues();
    	Parameters.time += Parameters.dt;
    
    }

	public void contact() {
	
		// find tranmissions between susceptibles and infecteds: S -> I
		for (Infected inf : infecteds) {
		
			double newInfections = Parameters.beta * susceptible.getValue() * inf.getValue() * Parameters.dt;
			susceptible.decrementStoredValue(newInfections);
			inf.incrementStoredValue(newInfections);
			
		}
		
		// find transmissions between recovereds and infecteds: R -> I
		for (Infected inf : infecteds) {
			for (Recovered rec : recovereds) {

				double xDist = Math.abs(inf.getXPos() - rec.getXPos());
				double yDist = Math.abs(inf.getYPos() - rec.getYPos());
				double euclideanDistance = Math.sqrt(xDist*xDist + yDist*yDist);
				double transmissability = euclideanDistance * Parameters.riskByDistance;
				if (transmissability > 1) { transmissability = 1; }
				
				double newInfections = Parameters.beta * transmissability * rec.getValue() * inf.getValue() * Parameters.dt;
				rec.decrementStoredValue(newInfections);
				inf.incrementStoredValue(newInfections);

			}
		}		
		
	}
	
	public void recovery() {
	
		// find recoveries of infecteds: I -> R
		for (Infected inf : infecteds) {
		
			double x = inf.getXPos();
			double y = inf.getYPos();
			Recovered rec = recovereds.getPosition(x, y);
			
			double newRecoveries = Parameters.nu * inf.getValue() * Parameters.dt;
			inf.decrementStoredValue(newRecoveries);
			rec.incrementStoredValue(newRecoveries);
			
		}
	
	}
		
	public void pushStoredValues() {
	
		susceptible.pushStoredValue();
	
		for (Infected inf : infecteds) {
			inf.pushStoredValue();
		}
		
		for (Recovered rec : recovereds) {
			rec.pushStoredValue();
		}		
	
	}

}