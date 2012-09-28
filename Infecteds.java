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

	public void print() {
        for (Infected inf : infecteds) {
        	inf.print();
        }	
	}
	
	

}