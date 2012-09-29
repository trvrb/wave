import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Infecteds implements Iterable<Infected> {

    private Set<Infected> infecteds = new HashSet<Infected>();
    private Set<Infected> queuedInfecteds = new HashSet<Infected>();
	
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
    	
    	// if no Infected exists, add one to queue
    	Infected inf = new Infected(x, y, 0);
    	queuedInfecteds.add(inf);
    	return inf;
    	
	}      
	
	public void dequeue() {
	
		infecteds.addAll(queuedInfecteds);
		queuedInfecteds.clear();
	
	}
	
	public void clean() {

//		List<Infected> drop = new ArrayList<Infected>;	
//		for (Infected inf : infecteds) {
//			if (inf.getValue() < Parameters.threshold) {
//				infecteds.remove(inf);
//			}
//		}

	}
  
	public void print() {
        for (Infected inf : infecteds) {
        	inf.print();
        }	
	}
	
	public Iterator<Infected> iterator(){

		final Iterator<Infected> i = infecteds.iterator();

		return new Iterator<Infected>() {	
			public boolean hasNext(){
				return i.hasNext();
			}
			public Infected next(){
				return i.next(); 
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		
	}    	

}