/**
 * Channel class
 * 
 * @author Dmitri Samoilov
 *
 */
public class Channel {

	public Day monday = new Day();
	public Day tuesday = new Day();	
	public Day wednesday = new Day();	
	public Day thursday = new Day();	
	public Day friday = new Day();	
	public Day saturday = new Day();	
	public Day sunday = new Day();	
	
	/**
	 * To hold currently selected day 
	 */
	public Day currentDay;
	
	Channel() {
	
	}
}
