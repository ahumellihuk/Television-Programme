/**
 * Programme of type Drama
 * 
 * @author Dmitri Samoilov
 *
 */
public class Drama extends Programme {

	/**
	 * Programme director
	 */
	protected String director;
	/**
	 * Programme actors
	 */
	protected String actors;
	/**
	 * Programme synopsis
	 */
	protected String synopsis;
	/**
	 * Programme production year
	 */
	protected int year;
	
	/**
	 * Creates an instance of Drama
	 * 
	 * @param t Programme title
	 * @param d Programme duration in minutes
	 * @param di Programme director
	 * @param a Programme actors
	 * @param s Programme synopsis
	 * @param y Programme production year
	 */
	public Drama(String t, int d, String di, String a, String s, int y) {
		super(t, d);
		director = di;
		actors = a;
		synopsis = s;
		year = y;
		type = "Drama";
	}
	
	public String toString() {
		return(title+", "+duration+", "+year+", Drama");
	}
	
	public String info() {
		return("<html>Title: "+title+"<br><br>Duration: "+duration+" minutes<br><br>Director: "
				+director+"<br><br>Actors: "+actors+"<br><br>Synopsis: "+synopsis+"<br><br>Year: "+year+"<br><br>Type: Drama");
	}

}