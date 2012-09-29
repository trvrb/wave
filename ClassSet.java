import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ClassSet implements Iterable<Class> {

	private String name;
    private Set<Class> set = new HashSet<Class>();
    private Set<Class> queue = new HashSet<Class>();

	// begin with am empty set
    public ClassSet(String n) {
    	name = n;  
    }
	
	// begin with a single instance
    public ClassSet(String n, double x, double y, double v) {
    	name = n;
    	Class c = new Class(n, x, y, v);
    	set.add(c);	    
    }
    
	public Class getPosition(double x, double y) {
	
		// return Class matching this position
    	for (Class c : set) {
    		double xDist = Math.abs(c.getXPos() - x);
    		double yDist = Math.abs(c.getYPos() - y);
    		if (xDist < 0.5 * Parameters.dxy && yDist < 0.5 * Parameters.dxy) {
    			return c;
    		}
    	}
    	
    	// if no matching Class exists, add one to queue
    	Class c = new Class(name, x, y, 0);
    	queue.add(c);
    	return c;
    	
	}      
	
	public void dequeue() {
	
		set.addAll(queue);
		queue.clear();
	
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
        for (Class c : set) {
        	c.print();
        }	
	}
	
	public Iterator<Class> iterator(){

		final Iterator<Class> i = set.iterator();

		return new Iterator<Class>() {	
			public boolean hasNext(){
				return i.hasNext();
			}
			public Class next(){
				return i.next(); 
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		
	}    	

}