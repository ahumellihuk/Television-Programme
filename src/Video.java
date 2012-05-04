/**
 * Programme of type Video
 * 
 * @author Dmitri Samoilov
 *
 */
public class Video extends Programme {

	/**
	 * Creates an instance of Video
	 * 
	 * @param t Programme title
	 * @param d Programme duration in minutes
	 */
	public Video(String t, int d) {
		super(t, d);
		type = "Video";
	}
	
	public String toString() {
		return(title+", "+duration+", Video");
	}
	
	public String info() {
		return("<html>Title: "+title+"<br><br>Duration: "+duration+" minutes<br><br> Type: Video");
	}

}
