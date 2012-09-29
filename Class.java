// A class is a group of identical hosts.  Contains phenotype and quantity.

public class Class {

	private String name;
	private double xPos;
	private double yPos;	
	private double value;
	private double storedValue;
	
    public Class(String n, double x, double y, double v) {
    	name = n;
    	xPos = x;
    	yPos = y;
    	value = v;    
    	storedValue = v;    	
    }
    
    public String getName() {
    	return name;
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
		System.out.println(name + " (" + xPos + "," + yPos + ") = " + value);
	}
		
}