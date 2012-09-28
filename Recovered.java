// Hosts recovered to a single strain.  Contains phenotype and quantity.

public class Recovered {

	private double phenotype;
	private double value;
	private double storedValue;
	
    public Recovered(double p, double v) {
    	phenotype = p;
    	value = v;  
    	storedValue = v;
    }
	
	public void updateStoredValue(double dx) {
		storedValue += dx;
		if (storedValue > 1)
			storedValue = 1;
		if (storedValue < 0)
			storedValue = 0;
	}
	
	public void pushStoredValue() {
		value = storedValue;
	}

	public void print() {
		System.out.println("R " + phenotype + " = " + value);
	}
		
}