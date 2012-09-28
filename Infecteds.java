import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Infecteds implements Iterable<Infected> {

    private List<Infected> infecteds = new ArrayList<Infected>();
	
    public Infecteds() {
    	Infected inf = new Infected(Parameters.initialIPosition, 0, Parameters.initialIProportion);
    	infecteds.add(inf);	    
    }
    
	public Infected getPosition(double x, double y) {
	
		// return Infected matching this position
    	for (Infected inf : infecteds) {
    		double xDist = Math.abs(inf.getXPos() - x);
    		double yDist = Math.abs(inf.getYPos() - y);
    		if (xDist < 0.5 * Parameters.dxy && yDist < 0.5 * Parameters.dxy) {
    			return inf;
    		}
    	}
    	
    	// if no Infected exists, create one
    	Infected inf = new Infected(x, y, 0);
    	infecteds.add(inf);
    	return inf;
    	
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