/**
 * Programme of type Still Picture
 * 
 * @author Dmitri Samoilov
 *
 */
public class Still_Picture extends Programme {

	/**
	 * Creates an instance of Still Picture
	 * 
	 * @param t Programme title
	 * @param d Programme duration in minutes
	 */
	public Still_Picture(String t, int d) {
		super(t, d);
		type = "Still Picture";
	}
	
	public String toString() {
		return(title+", "+duration+", Still Picture");
	}
	
	public String info() {
		return("<html>Title: "+title+"<br><br>Duration: "+duration+" minutes<br><br> Type: Still Picture");
	}

}