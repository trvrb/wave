// Hosts recovered to a single strain.  Contains phenotype and quantity.

public class Recovered {

	private double position;
	private double value;
	private double storedValue;
	
    public Recovered(double p, double v) {
    	position = p;
    	value = v;  
    	storedValue = v;
    }
    
    public double getPosition() {
    	return position;
    }
    
    public double getValue() {
    	return value;
    }        
    
    public void setValues(double x) {
    	value = x;
    	storedValue = x;
    }    
    	
	public void incrementStoredValue(double dx) {
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
		System.out.println("R " + position + " = " + value);
	}
		
}