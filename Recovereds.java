import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Recovereds implements Iterable<Recovered> {

    private List<Recovered> recovereds = new ArrayList<Recovered>();
	
    public Recovereds() {
    	Recovered rec = new Recovered(Parameters.initialRPosition, 0, Parameters.initialRProportion);
    	recovereds.add(rec);	    
    }
    
	public Recovered getPosition(double x, double y) {
	
		// return Recovered matching this position
    	for (Recovered rec : recovereds) {
    		double xDist = Math.abs(rec.getXPos() - x);
    		double yDist = Math.abs(rec.getYPos() - y);
    		if (xDist < 0.5 * Parameters.dxy && yDist < 0.5 * Parameters.dxy) {
    			return rec;
    		}
    	}
    	
    	// if no Recovered exists, create one
    	Recovered rec = new Recovered(x, y, 0);
    	recovereds.add(rec);
    	return rec;
    	
    }
    	
	public void print() {
        for (Recovered rec : recovereds) {
        	rec.print();
        }	
	}
	
	public Iterator<Recovered> iterator(){

		return new Iterator<Recovered>() {
			private int count=0;
		
			public boolean hasNext(){
				return count < recovereds.size();
			}
		
			public Recovered next(){
				return recovereds.get(count++); 
			}
		
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		
	}    		

}