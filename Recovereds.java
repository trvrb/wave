import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Recovereds implements Iterable<Recovered> {

    private List<Recovered> recovereds = new ArrayList<Recovered>();
	
    public Recovereds() {
    	Recovered rec = new Recovered(Parameters.initialRPosition, Parameters.initialRProportion);
    	recovereds.add(rec);	    
    }
    
	public Recovered getPosition(double pos) {
	
		// return Recovered matching this position
    	for (Recovered rec : recovereds) {
    		double dist = Math.abs(rec.getPosition() - pos);
    		if (dist < 0.5 * Parameters.dx)
    			return rec;
    	}
    	
    	// if no Recovered exists, create one
    	Recovered rec = new Recovered(pos, 0);
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