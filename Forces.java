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
    
    	birthDeath();
 		contact();
 		recovery();
 		mutation();
    	pushStoredValues();
    	clean();
    	Parameters.time += Parameters.dt;
    
    }
      
    public void birthDeath() {
    
    	// an equal number of hosts are born into and die from the susceptible pool: S -> S 
    	
    	for (Infected inf : infecteds) {
    		double newDeaths = Parameters.birthDeath * inf.getValue() * Parameters.dt;
    		inf.decrementStoredValue(newDeaths);
    		susceptible.incrementStoredValue(newDeaths);
    	}
    	
    	for (Recovered rec : recovereds) {
    		double newDeaths = Parameters.birthDeath * rec.getValue() * Parameters.dt;
    		rec.decrementStoredValue(newDeaths);
    		susceptible.incrementStoredValue(newDeaths);
    	}    	
    
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
	
	public void mutation() {
	
		// infecteds mutate to new varieties of infecteds: I_i -> I_j
		for (Infected inf : infecteds) {
		
			double x = inf.getXPos();
			double y = inf.getYPos();
		
			double split = 0.5;
			if (Parameters.mutation2D) {
				split = 0.25;
			}
			double newMutations = split * Parameters.mu * inf.getValue() * Parameters.dt;
		
			if (newMutations > 0) {
		
				// LEFT MUTATIONS
				Infected leftMut = infecteds.getPosition(x - Parameters.dxy, y);
				inf.decrementStoredValue(newMutations);
				leftMut.incrementStoredValue(newMutations);
				
				// RIGHT MUTATIONS
				Infected rightMut = infecteds.getPosition(x + Parameters.dxy, y);
				inf.decrementStoredValue(newMutations);
				rightMut.incrementStoredValue(newMutations);		
				
				if (Parameters.mutation2D) {
				
					// DOWN MUTATIONS
					Infected downMut = infecteds.getPosition(x, y - Parameters.dxy);
					inf.decrementStoredValue(newMutations);
					downMut.incrementStoredValue(newMutations);
					
					// UP MUTATIONS
					Infected upMut = infecteds.getPosition(x, y + Parameters.dxy);
					inf.decrementStoredValue(newMutations);
					upMut.incrementStoredValue(newMutations);				
				
				}
			
			}
		
		}
		
		infecteds.dequeue();
	
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
	
	public void clean() {
		infecteds.clean();
	}

}