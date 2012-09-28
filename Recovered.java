// Hosts recovered to a single strain.  Contains phenotype and quantity.

public class Recovered {

	private double xPos;
	private double yPos;	
	private double value;
	private double storedValue;
	
    public Recovered(double x, double y, double v) {
    	xPos = x;
    	yPos = y;
    	value = v;  
    	storedValue = v;
    }
    
    public double getXPos() {
    	return xPos;
    }
    
    public double getYPos() {
    	return yPos;
    }    
    
    public double getValue() {
    	return value;
    }        
    
    public void setValues(double v) {
    	value = v;
    	storedValue = v;
    }    
    	
	public void incrementStoredValue(double dx) {
		storedValue += dx;
		if (storedValue > 1) {
			storedValue = 1;
		}
	}
	
	public void decrementStoredValue(double dx) {
		storedValue -= dx;
		if (storedValue < 0) {
			storedValue = 0;
		}
	}
	
	public void pushStoredValue() {
		value = storedValue;
	}

	public void print() {
		System.out.println("R (" + xPos + "," + yPos + ") = " + value);
	}
		
}