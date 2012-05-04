/**
 * Abstract programme class
 * 
 * @author Dmitri Samoilov
 *
 */
public abstract class Programme {
	/**
	 * Programme title	
	 */
	protected String title;
	/**
	 * Programme duration in minutes
	 */
	protected int duration;
	/**
	 * Programme start time in minutes
	 */
	protected int startTime;
	/**
	 * Programme type
	 */
	protected String type;
	/**
	 * Whether programme is rentable or not
	 */
	protected boolean rentable;
	/**
	 * trailer duration for rentable items
	 */
	protected int trailerDuration;
	
	/**
	 * Creates an instance of programme; Sets start time to 0 by default.
	 * 
	 * @param t Programme title
	 * @param d Programme duration in minutes
	 * @param tp Programme type
	 */
	public Programme(String t, int d) {
		title = t;
		duration = d;
		startTime = 0;
		type = "";
		rentable = false;
		trailerDuration=0;
	}
	/**
	 * @return Basic programme information
	 */
	public String toString() {
		return(title+", "+duration);
	}
	/**
	 * Generates a String with all information about a programme
	 * 
	 * @return Full programme information
	 */
	public String info() {
		return("<html>Title: "+title+"<br><br>Duration: "+duration+" minutes<br><br>");
	}
	/**
	 * 
	 * @return Programme title
	 */
	public String getTitle() {
		return(title);
	}
	/**
	 * 
	 * @return Programme duration in minutes
	 */
	public int getDuration() {
		return(duration);
	}
	/**
	 * 
	 * @return Programme start time in minutes
	 */
	public int getStartTime() {
		return(startTime);
	}
	/**
	 * 
	 * @return Programme type
	 */
	public String getType() {
		return(type);
	}
	/**
	 * Sets a start time
	 * 
	 * @param time Time in minutes
	 */
	public void setStartTime(int time) {
		startTime = time;
	}
	
	public void setRentable(Boolean set) {
		rentable = set;
	}
	
	public boolean getRentable() {
		return(rentable);
	}

}
