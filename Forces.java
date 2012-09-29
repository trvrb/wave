/* Epidemiological forces.  Contains references to classes to update. */

public class Forces {

	private Class susceptible;
	private ClassSet infecteds;
	private ClassSet recovereds;

    public Forces(Class sus, ClassSet inf, ClassSet rec) {
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
    	
    	for (Class inf : infecteds) {
    		double newDeaths = Parameters.birthDeath * inf.getValue() * Parameters.dt;
    		inf.decrementStoredValue(newDeaths);
    		susceptible.incrementStoredValue(newDeaths);
    	}
    	
    	for (Class rec : recovereds) {
    		double newDeaths = Parameters.birthDeath * rec.getValue() * Parameters.dt;
    		rec.decrementStoredValue(newDeaths);
    		susceptible.incrementStoredValue(newDeaths);
    	}    	
    
    }

	public void contact() {
	
		// find tranmissions between susceptibles and infecteds: S -> I
		for (Class inf : infecteds) {
		
			double newInfections = Parameters.beta * susceptible.getValue() * inf.getValue() * Parameters.dt;
			susceptible.decrementStoredValue(newInfections);
			inf.incrementStoredValue(newInfections);
			
		}
		
		// find transmissions between recovereds and infecteds: R -> I
		for (Class inf : infecteds) {
			for (Class rec : recovereds) {

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
		for (Class inf : infecteds) {
		
			double x = inf.getXPos();
			double y = inf.getYPos();
			Class rec = recovereds.getPosition(x, y);
			
			double newRecoveries = Parameters.nu * inf.getValue() * Parameters.dt;
			inf.decrementStoredValue(newRecoveries);
			rec.incrementStoredValue(newRecoveries);
			
		}
	
	}
	
	public void mutation() {
	
		// infecteds mutate to new varieties of infecteds: I_i -> I_j
		for (Class inf : infecteds) {
		
			double x = inf.getXPos();
			double y = inf.getYPos();
		
			double split = 0.5;
			if (Parameters.mutation2D) {
				split = 0.25;
			}
			double newMutations = split * Parameters.mu * inf.getValue() * Parameters.dt;
		
			// LEFT MUTATIONS
			Class leftMut = infecteds.getPosition(x - Parameters.dxy, y);
			inf.decrementStoredValue(newMutations);
			leftMut.incrementStoredValue(newMutations);
			
			// RIGHT MUTATIONS
			Class rightMut = infecteds.getPosition(x + Parameters.dxy, y);
			inf.decrementStoredValue(newMutations);
			rightMut.incrementStoredValue(newMutations);		
			
			if (Parameters.mutation2D) {
			
				// DOWN MUTATIONS
				Class downMut = infecteds.getPosition(x, y - Parameters.dxy);
				inf.decrementStoredValue(newMutations);
				downMut.incrementStoredValue(newMutations);
				
				// UP MUTATIONS
				Class upMut = infecteds.getPosition(x, y + Parameters.dxy);
				inf.decrementStoredValue(newMutations);
				upMut.incrementStoredValue(newMutations);				
			
			}
		
		}
		
		infecteds.dequeue();
	
	}
		
	public void pushStoredValues() {
	
		susceptible.pushStoredValue();
	
		for (Class inf : infecteds) {
			inf.pushStoredValue();
		}
		
		for (Class rec : recovereds) {
			rec.pushStoredValue();
		}		
	
	}
	
	public void clean() {
		infecteds.clean();
		recovereds.clean();
	}

}