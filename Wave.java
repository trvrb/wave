public class Wave {
    public static void main(String[] args) {

		Susceptible sus = new Susceptible();
		Infecteds inf = new Infecteds(0, 10, 0.5);	
		Recovereds rec = new Recovereds(0, 10, 0.5);
		
		sus.initialize();
		inf.initialize();		
		rec.initialize();
		
		sus.print();
		inf.print();	
		rec.print();	

	}
}