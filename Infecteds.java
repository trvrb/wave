import java.util.ArrayList;
import java.util.List;

public class Infecteds {

    private List<Infected> infecteds = new ArrayList<Infected>();
	
    public Infecteds(double min, double max, double dx) {
    
    	for (double x=min; x<max; x+=dx) {
    		Infected inf = new Infected(x, 0);
    		infecteds.add(inf);
    	}
    	    
    }
    
    public void initialize() {
    
    	// find closest index to position
    	int index = 0;
    	double dist = 100;
    	for (int i = 0; i < infecteds.size(); i++) {
    		Infected inf = infecteds.get(i);
    		double thisDist = Math.abs(inf.getPosition() - Parameters.initialIPosition);
    		if (thisDist < dist) {
    			dist = thisDist;
    			index = i;
    		}
    	}
    	
    	// update this recovered
    	Infected inf = infecteds.get(index);
    	inf.setValues(Parameters.initialIProportion);
    
    }        

	public void print() {
        for (Infected inf : infecteds) {
        	inf.print();
        }	
	}
	
	

}