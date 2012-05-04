/**
 * Programme of type Music Video
 * 
 * @author Dmitri Samoilov
 *
 */
public class Music_Video extends Programme {
	/**
	 * Music Video artists
	 */
	protected String artists; 
	/**
	 * Music Video production year
	 */
	protected int year;
	
	/**
	 * Creates an instance of Music Video
	 * 
	 * @param t	Programme title
	 * @param d Programme duration in minutes
	 * @param a Programme artists
	 * @param y Programme production year
	 */
	public Music_Video(String t, int d, String a, int y) {
		super(t, d);
		artists = a;
		year = y;
		type = "Music Video";
	}
	
	public String toString() {
		return(title+", "+duration+", "+year+", Music Video");
	}
	
	public String info() {
		return("<html>Title: "+title+"<br><br>Duration: "+duration+" minutes<br><br>Artists: "
				+artists+"<br><br>Year: "+year+"<br><br>Type: Music Video");
	}

}
