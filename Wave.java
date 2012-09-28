public class Wave {
    public static void main(String[] args) {

		Susceptible susceptible = new Susceptible();
		Infecteds infecteds = new Infecteds(0, 10, 0.5);	
		Recovereds recovereds = new Recovereds(0, 10, 0.5);
		
		susceptible.initialize();
		infecteds.initialize();		
		recovereds.initialize();
		
//		susceptible.print();
//		infecteds.print();	
//		recovereds.print();	
		
		for (Infected inf : infecteds) {
			inf.print();
		}
		
		for (Recovered rec : recovereds) {
			rec.print();
		}		

	}
}