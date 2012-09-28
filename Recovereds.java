import java.util.ArrayList;
import java.util.List;

public class Recovereds {

    private List<Recovered> recovereds = new ArrayList<Recovered>();
	
    public Recovereds(double min, double max, double dx) {
    
    	for (double x=min; x<max; x+=dx) {
    		Recovered rec = new Recovered(x, 0);
    		recovereds.add(rec);
    	}
    	    
    }
    
    public void initialize() {
    
    	// find closest index to position
    	int index = 0;
    	double dist = 100;
    	for (int i = 0; i < recovereds.size(); i++) {
    		Recovered rec = recovereds.get(i);
    		double thisDist = Math.abs(rec.getPosition() - Parameters.initialRPosition);
    		if (thisDist < dist) {
    			dist = thisDist;
    			index = i;
    		}
    	}
    	
    	// update this recovered
    	Recovered rec = recovereds.get(index);
    	rec.setValues(Parameters.initialRProportion);
    
    }    

	public void print() {
        for (Recovered rec : recovereds) {
        	rec.print();
        }	
	}
	
	

}