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

	public void print() {
        for (Recovered rec : recovereds) {
        	rec.print();
        }	
	}
	
	

}