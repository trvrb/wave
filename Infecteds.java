import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Infecteds implements Iterable<Infected> {

    private List<Infected> infecteds = new ArrayList<Infected>();
	
    public Infecteds() {
    	Infected inf = new Infected(Parameters.initialIPosition, Parameters.initialIProportion);
    	infecteds.add(inf);	    
    }
    
	public Infected getPosition(double pos) {
	
		// return Infected matching this position
    	for (Infected inf : infecteds) {
    		double dist = Math.abs(inf.getPosition() - pos);
    		if (dist < 0.5 * Parameters.dx)
    			return inf;
    	}
    	
    	// if no Infected exists, create one
    	Infected inf = new Infected(pos, 0);
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