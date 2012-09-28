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

	public void contact() {
	
		// find tranmissions from infecteds to susceptibles
		for (Infected inf : infecteds) {
			double newInfections = Parameters.beta * susceptible.getValue() * inf.getValue();
			susceptible.incrementStoredValue(-1*newInfections);
			inf.incrementStoredValue(newInfections);
		}
		
		// find transmission from infecteds to recovereds
		for (Infected inf : infecteds) {
			for (Recovered rec : recovereds) {

				double xDist = Math.abs(inf.getXPos() - rec.getXPos());
				double yDist = Math.abs(inf.getYPos() - rec.getYPos());
				double euclideanDistance = Math.sqrt(xDist*xDist + yDist*yDist);
				double transmissability = euclideanDistance * Parameters.riskByDistance;
				if (transmissability > 1) { transmissability = 1; }
				
				double newInfections = Parameters.beta * transmissability * rec.getValue() * inf.getValue();
				rec.incrementStoredValue(-1*newInfections);
				inf.incrementStoredValue(newInfections);

			}
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