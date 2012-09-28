import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Infecteds implements Iterable<Infected> {

    private List<Infected> infecteds = new ArrayList<Infected>();
	
    public Infecteds(double min, double max, double dx) {
    
    	for (double x=min; x<max; x+=dx) {
    		Infected inf = new Infected(x, 0);
    		infecteds.add(inf);
    	}
    	    
    }
    
    public void initialize() {
    	Infected inf = getPosition(Parameters.initialIPosition);
    	inf.setValues(Parameters.initialIProportion);
    }    

	// return closest Infected to this position
	public Infected getPosition(double pos) {
	
		// find closest index to position
		int index = 0;
    	double dist = 100;
    	for (int i = 0; i < infecteds.size(); i++) {
    		Infected inf = infecteds.get(i);
    		double thisDist = Math.abs(inf.getPosition() - pos);
    		if (thisDist < dist) {
    			dist = thisDist;
    			index = i;
    		}
    	}
    	
    	return infecteds.get(index);
    	
	}      
  
	public void print() {
        for (Infected inf : infecteds) {
        	inf.print();
        }	
	}
	
	public Iterator<Infected> iterator(){

		return new Iterator<Infected>() {
			private int count=0;
		
			public boolean hasNext(){
				return count < infecteds.size();
			}
		
			public Infected next(){
				return infecteds.get(count++); 
			}
		
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		
	}    	

}