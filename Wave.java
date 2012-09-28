public class Wave {
    public static void main(String[] args) {

		Susceptible susceptible = new Susceptible();
		Infecteds infecteds = new Infecteds();	
		Recovereds recovereds = new Recovereds();
		Forces forces = new Forces(susceptible, infecteds, recovereds);
	
		susceptible.print();
		infecteds.print();	
		recovereds.print();	
		
		forces.step();
		
		susceptible.print();
		infecteds.print();	
		recovereds.print();	
		
		forces.step();
		
		susceptible.print();
		infecteds.print();	
		recovereds.print();			

	}
}