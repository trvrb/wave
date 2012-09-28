public class Wave {
    public static void main(String[] args) {

		Susceptible sus = new Susceptible();
		Infecteds inf = new Infecteds(0, 10, 0.1);	
		Recovereds rec = new Recovereds(0, 10, 0.1);

		sus.print();
		inf.print();	
		rec.print();	

	}
}