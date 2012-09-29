public class Wave {
    public static void main(String[] args) {

		Class susceptible = new Class("sus", 0, 0, 1 - Parameters.initialIProportion - Parameters.initialRProportion);
		ClassSet infecteds = new ClassSet("inf", Parameters.initialIPosition, 0, Parameters.initialIProportion);	
		ClassSet recovereds = new ClassSet("rec", Parameters.initialRPosition, 0, Parameters.initialRProportion);
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
		
		forces.step();
		
		susceptible.print();
		infecteds.print();	
		recovereds.print();			

	}
}