public class Wave {
    public static void main(String[] args) {

		Class susceptible = new Class("sus", 0, 0, 1 - Parameters.initialIProportion - Parameters.initialRProportion);
		ClassSet infecteds = new ClassSet("inf", Parameters.initialIPosition, 0, Parameters.initialIProportion);	
		ClassSet recovereds = new ClassSet("rec", Parameters.initialRPosition, 0, Parameters.initialRProportion);
		Forces forces = new Forces(susceptible, infecteds, recovereds);
					
		System.out.println("time\tfS\tfI\tfR\txI\txR");
		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n", Parameters.time, susceptible.getValue(), infecteds.getValue(), recovereds.getValue(), infecteds.getMeanX(), recovereds.getMeanX());
		
		for (int i=0; i<1000; i++) {
			forces.step();
			if (Parameters.time % 1 < 0.5*Parameters.dt || Parameters.time % 1 > 1 - 0.5*Parameters.dt) {
				System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n", Parameters.time, susceptible.getValue(), infecteds.getValue(), recovereds.getValue(), infecteds.getMeanX(), recovereds.getMeanX());
			}
		}
		
	//	infecteds.print();
		
	}
}