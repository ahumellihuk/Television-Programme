import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.util.*;

/**
 * Creates a day
 * 
 * Contains scheduling methods
 * 
 * @author Dmitri Samoilov
 *
 */
public class Day {
	
		/**
		 * Hash map to hold day schedule
		 */
		public Map<Integer,Programme> schedule;
		/**
		 * Start time of current time slot in minutes
		 */
		public int startSlot;
		/**
		 * End time of current time slot in minutes
		 */
		public int endSlot;
		/**
		 * Number of currently available time slots
		 */
		public int slots;
		
		/**
		 * Day constructor
		 * 
		 * Initialises hash map to 36 elements (maximum number of 30-minute programmes)
		 * Sets time slot from 6AM to 12AM and number of slots to 1
		 */
		public Day() {
			schedule = new HashMap<Integer,Programme>(36);
			
			for (int i=360; i<=1440; i+=30) {
				schedule.put(i, null);
			}
			
			startSlot = 360;
			endSlot = 1440;
			slots = 1;
		}
		
		/**
		 * Adds a programme to first available time in a slot
		 * 
		 * @param x A programme to add to the schedule
		 */
		public void addFirstAvailable(Programme x) {
			
			int n=check(x.duration, startSlot);
			
			if (n>=(x.duration/30)) {
				
				String time=convertTime(startSlot);
				x.startTime=startSlot;
				
				for (int i=startSlot; i<startSlot+x.duration; i+=30) {
					schedule.put(i, x);
				}
				startSlot=startSlot+n*30;
				time+=" - "+convertTime(startSlot);
				System.out.println(time+"  -  "+x.getTitle());
				
				timeSlot();
			}
			else JOptionPane.showMessageDialog(null,"There is not enough time for this programme. The time available is "+convertTime(n*30));
		}
		
		/**
		 * Adds a programme to a particular time, specified by user
		 * 
		 * @param x	A programme to add to the schedule
		 * @param request	Time to add programme to (in minutes)
		 */
		public void addToTime(Programme x, int request) {
			
			String time="";
			int n=check(x.duration, request);
			
				if (n>=(x.duration/30)) {
				
					x.startTime = request;	
					
					for (int i=request; i<request+x.duration; i+=30)
						schedule.put(i,x);
						
					time+=convertTime(request);
					time+=(" - "+convertTime((request+x.duration)));
			
					System.out.println(time+"  -  "+x.getTitle());
					
					timeSlot();
				}
				else if (schedule.get(request)!=null) System.out.println("There is a program scheduled at requested time");
				else if (n<(x.duration/30) )System.out.println("Not enought time. Time available is "+convertTime(n*30));
				
		}
		
		/**
		 * Deletes a programme from the schedule
		 * 
		 * @param time	Start time of a programme that needs to be deleted (in minutes)
		 */
		public void delete(int time) {
			
			if (schedule.get(time)!=null) {
				int n = schedule.get(time).duration;
			
				for (int i=time; i<time+n; i+=30) {
					schedule.put(i,null);
				}
				timeSlot();
			}
		}			
						
		/**
		 * Checks whether there is enough time in the schedule for a particular programme
		 * 
		 * @param duration	The duration of a programme in minutes
		 * @param time	Time in minutes to begin checking from
		 * @return	Number of 30-minute slots available
		 */
		public int check(int duration ,int time) {
			
			int n=0;			
			if (schedule.get(time)==null && time<1440) {
				n++;				
				for (int i=time+30; i<time+duration; i+=30) {
					if (schedule.get(i)==null && i<1440) n++;
				}				
			}			
			return(n);
			
		}
		
		/**
		 * Generates a list of scheduled programmes
		 * 
		 * @return	Pre-formatted string containing a list of scheduled programmes, to use within a JLabel
		 */
		public String displayList() {
		
			String output = "";

			for (int i=360; i<=1440; i+=30) {
			
				if (schedule.get(i)!=null) {
								
					output+=convertTime(i)+"-"+convertTime(i+schedule.get(i).duration)+" - "+schedule.get(i).getTitle()+"<br>";
					i+=(schedule.get(i).duration-30);
				}
			}
		
			if (output!="")	return(output);
			else return("NO ITEMS ON SCHEDULE\n");
		}
		
		/**
		 * Initialises an array with a number of Radio buttons that represent the scheduled programmes
		 * 
		 * @param array	An array of JRadioButtons
		 * @param group	A ButtonGroup for JRadioButtons
		 * @return	Number of radio buttons initialised
		 */
		public int radioList(JRadioButton [] array, ButtonGroup group) {
			int a=0;
			for (int i=360; i<=1440; i+=30) {
				
				if (schedule.get(i)!=null) {
								
					array[a]= new JRadioButton(convertTime(i)+"-"+convertTime(i+schedule.get(i).duration)+" - "+schedule.get(i).getTitle());
					group.add(array[a]);
					i+=(schedule.get(i).duration-30);
					a++;
				}
			}
			return(a);
		}
		
		/**
		 * Determines number of time slots currently available
		 * 
		 * @return	Number of time slots available
		 */
		public String[] timeSlot() {
			
			String [] select = new String [10];
			int slotStart, slotEnd;
			int a=0;
			slots=0;
			
			for (int i=360; i<1440; i+=30) {
				
				if (schedule.get(i)==null) {
					
					slots++;
					slotStart = i;
					a=i+30;
					
					while (schedule.get(a)==null && a<=1410) 	a+=30;
					
					slotEnd = a;
					i=a-30;
					
					select[slots-1]=convertTime(slotStart)+"-"+convertTime(slotEnd);
				}
			}
			
			setSlot(1);
			
			return(select);
		}
		
		/**
		 * Sets a time slot to use
		 * 
		 * @param n Time slot id, in order of discovery
		 */
		public void setSlot(int n) {
			
			int slotStart = 360;
			int slotEnd = 1440;
			int a=360;
			int found=0;
			
			for (int i=360; i<1440; i+=30) {
				
				if (schedule.get(i)==null) {
					found++;
					slotStart = i;
					a=i+30;
					
					while (schedule.get(a)==null && a<=1410) a+=30;
					
					slotEnd=a;
					i=a-30;
				}
				
				if (found==n) {
					startSlot=slotStart;
					endSlot=slotEnd;
				}
			}
		}
		
		
		/**
		 * Converts time from minutes to a String of HH:MM format
		 * 
		 * @param x Time in minutes
		 * @return Time in HH:MM format
		 */
		public String convertTime(int x) {
			
			String time = "";
			
			if (x%60==0) time+=(x/60+":00");
			else time+=(x/60+":30");
			
			return(time);
		}
}