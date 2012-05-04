import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Display class creates Graphical User Interface
 * 
 * @author Dmitri Samoilov
 *
 */
public class Display implements ActionListener{
	/**
	 * An array to hold all available programmes
	 */
	public Programme [] dbase;
	public Channel one;				//Channel one
	public Channel two;				//Channel two
	/**
	 * Array of Strings for available programmes
	 */
	public String [] menu;
	/**
	 * To hold currently selected channel
	 */
	public Channel currentChannel;
	/**
	 * Number of items currently in the schedule (dynamic)
	 */
	public int noOfItems;
	
	public String [] types = {"All", "Still Picture", "Video", "Film", "Comedy", "Drama", "Music Video"};
	
	
	JFrame frame;
	JPanel south,bottom,north,channel,day, buttons, details, removePanel, infoPanel;
	JButton channel1,channel2,monday,tuesday,wednesday,thursday,friday,saturday,sunday;
	JButton add,addto,remove,view,rentable,submit,submitTo,submitRemove,exit;
	JComboBox select,slot,viewSelect, typeSelect, trailerDuration;
	JTextField time;
	JLabel info;
	ButtonGroup group;
	JRadioButton [] radio = new JRadioButton[36];
	JCheckBox rent;
	
	/**
	 * Main method creates an instance of Display class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Display();
	}
	/**
	 * Initialises database, creates list of available programmes and calls GUI method
	 */
	public Display() {
		
		dbase = new Programme[14];
		one = new Channel();
		two = new Channel();
		menu = new String[dbase.length];
		noOfItems = 0;
		
		Database.initialise(dbase);
		
		for (int i=0;i<dbase.length;i++)
		   menu[i]=dbase[i].toString();	//Create list of available programmes
		
		
		
		createGUI();
	}
	/**
	 * Creates all GUI elements
	 */
	public void createGUI() {
		
		//Set up Frame
		frame = new JFrame("Scheduling System");
		frame.setSize(640,480);
		
		//South Panel
		south = new JPanel();
		south.setLayout(new GridLayout(3,1));
		
		//Bottom
		bottom = new JPanel();
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		view = new JButton("View programme info");
		view.addActionListener(this);
		rentable = new JButton("Rentable Programmes");
		rentable.addActionListener(this);
		bottom.add(rentable);
		bottom.add(view);
		bottom.add(exit);
		
		//Buttons
		buttons = new JPanel();		
		
		add = new JButton("Add to first time available");
		add.addActionListener(this);
		addto = new JButton("Add to particular time");
		addto.addActionListener(this);
		remove = new JButton("Remove programme");
		remove.addActionListener(this);
		
		//Details
		details = new JPanel();
		details.setLayout(new GridLayout(1,4));
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
		submitTo = new JButton("Submit");
		submitTo.addActionListener(this);
		submitRemove = new JButton("Submit");
		submitRemove.addActionListener(this);
		select = new JComboBox(menu);		
		select.addActionListener(this);
		slot = new JComboBox();
		slot.addActionListener(this);
		time = new JTextField("Enter the time");
		
		south.add(details, 0);
		south.add(buttons, 1);
		south.add(bottom, 2);
		frame.add(south, BorderLayout.SOUTH);
		
		//Upper Panel
		north = new JPanel();
		north.setLayout(new GridLayout(2,1));
		channel = new JPanel();
		channel.setLayout(new GridLayout(1,2));
		day = new JPanel();
		
		channel1 = new JButton("Channel One");
		channel1.addActionListener(this);
		channel2 = new JButton("Channel Two");
		channel2.addActionListener(this);
		
		monday = new JButton("Monday");
		monday.addActionListener(this);
		tuesday = new JButton("Tuesday");
		tuesday.addActionListener(this);
		wednesday = new JButton("Wednesday");
		wednesday.addActionListener(this);
		thursday = new JButton("Thursday");
		thursday.addActionListener(this);
		friday = new JButton("Friday");
		friday.addActionListener(this);
		saturday = new JButton("Saturday");
		saturday.addActionListener(this);
		sunday = new JButton("Sunday");
		sunday.addActionListener(this);
		
		channel.add(channel1);
		channel.add(channel2);
		
		//Days
		day.add(monday);
		day.add(tuesday);
		day.add(wednesday);
		day.add(thursday);
		day.add(friday);
		day.add(saturday);
		day.add(sunday);
		
		north.add(channel);
		north.add(day);
		
		frame.add(north, BorderLayout.NORTH);
		
		//Remove Item Panel
		removePanel = new JPanel();
		
		//Central Info Panel
		infoPanel = new JPanel();
		frame.add(infoPanel, BorderLayout.CENTER);
		
		//Labels		
		info = new JLabel("<html>Welcome to the Scheduling System!<br><br>Please Select Channel.</html>", 0);
		infoPanel.add(info);
		
		//View Type Select
		typeSelect = new JComboBox(types);
		typeSelect.addActionListener(this);
		
		//View Select Item
		viewSelect = new JComboBox();
		
		for (int i=0; i<dbase.length; i++)
			viewSelect.addItem(dbase[i].getTitle());
		
		viewSelect.addActionListener(this);		
		
		//Rentable Checkbox
		rent = new JCheckBox("Rentable");
		rent.addActionListener(this);
		
		trailerDuration = new JComboBox();
		trailerDuration.addActionListener(this);
		
		for (int i=0; i<=10; i++) {
			trailerDuration.addItem(i + " minutes");
		}
		
		
		//Button Group
		group = new ButtonGroup();
		//Radio Buttons
		for (int i=0; i<36; i++) {
			radio[i] = new JRadioButton();
			radio[i].addActionListener(this);
		}
		//Set Frame Visible
		frame.setVisible(true);		
	}
	
	/**
	 * Action Listener for GUI elements
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == channel1) {	//Channel One
			currentChannel = one;
			currentChannel.currentDay = null;			
			clean(1);
			info.setText("<html>Channel One.<br><br>Please select Day.</html>");
			channel1.setText("<Channel One>");
			channel2.setText("Channel Two");
			
		}
		else if (e.getSource() == channel2) {	//Channel Two
			currentChannel = two;
			currentChannel.currentDay = null;			
			clean(1);
			info.setText("<html>Channel Two.<br><br>Please select Day.</html>");
			channel2.setText("<Channel Two>");
			channel1.setText("Channel One");
		}
		else if (e.getSource() == monday) {		//Monday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.monday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				monday.setText("<Monday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == tuesday) {	//Tuesday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.tuesday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				tuesday.setText("<Tuesday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == wednesday) {	//Wednesday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.wednesday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				wednesday.setText("<Wednesday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == thursday) {	//Thursday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.thursday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				thursday.setText("<Thursday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == friday) {	//Friday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.friday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				friday.setText("<Friday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == saturday) {	//Saturday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.saturday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				saturday.setText("<Saturday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == sunday) {	//Sunday
			clean(2);
			if (currentChannel != null) {
				currentChannel.currentDay = currentChannel.sunday;
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
				buttons.add(add);
				buttons.add(addto);
				buttons.add(remove);
				buttons.updateUI();
				sunday.setText("<Sunday>");
			}
			else info.setText("Please select a Channel first!");
		}
		else if (e.getSource() == add) {	//AddFirstAvailable
			infoPanel.removeAll();
			infoPanel.add(info);
			info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
			infoPanel.updateUI();
			details.removeAll();						
			if (currentChannel.currentDay.slots>1) {
				slot = new JComboBox(currentChannel.currentDay.timeSlot());
				details.add(slot,0);
			}
			details.add(select);
			details.add(submit,-1);
			frame.repaint();
		}
		else if (e.getSource() == addto) {	//AddToTime
			infoPanel.removeAll();
			infoPanel.add(info);
			info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
			infoPanel.updateUI();
			details.removeAll();			
			details.add(select);
			details.add(time);
			details.add(submitTo);
			frame.repaint();
		}
		else if (e.getSource() == remove) {	//Remove
			
			if (noOfItems!=0) {
				details.removeAll();
				details.updateUI();
				infoPanel.removeAll();
				removePanel.removeAll();
				noOfItems = currentChannel.currentDay.radioList(radio, group);
				removePanel.setLayout(new GridLayout(noOfItems, 1));
				for (int i = 0; i < noOfItems; i++) {
					removePanel.add(radio[i]);
				}
				infoPanel.add(removePanel);
				infoPanel.updateUI();
				details.add(submitRemove);
				frame.repaint();
			}
			else info.setText("No items on schedule!");
		}
		else if (e.getSource() == submit) {	//Submit
			
			if (currentChannel.currentDay.slots>1) 
				currentChannel.currentDay.setSlot(slot.getSelectedIndex()+1);
			
			int item = select.getSelectedIndex();			
			currentChannel.currentDay.addFirstAvailable(dbase[item]);
			noOfItems++;
			details.removeAll();
			details.updateUI();
			info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
		}
		else if (e.getSource() == submitTo) {	//SubmitTo
						
			int item = select.getSelectedIndex();
			String toTime = time.getText();
			
			if (toTime.matches("0[6-9]:[03]0") || toTime.matches("1[0-9]:[03]0") || toTime.matches("2[0-3]:[03]0") || toTime.matches("[6-9]:[03]0")) {
				currentChannel.currentDay.addToTime(dbase[item], convertString(toTime));
				noOfItems++;
				details.removeAll();
				details.updateUI();
				info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
			}				
			else 
				JOptionPane.showMessageDialog(null,"Please enter a correct time in a hh:mm format\n\nChannel air time is from 6AM to Midnight.");
		}
		else if (e.getSource() == submitRemove) {	//Submit Remove
			
			String text = "00:00";
			
			for (int i=0; i<noOfItems; i++) {
				
				if (radio[i].getModel() == group.getSelection()) {
					text = radio[i].getText();
					//System.out.println("Compare: "+radio[i].getModel().toString()+" and "+group.getSelection().toString());
				}					
			}
			
			if (text.charAt(4) == '-')
				text = text.substring(0, 4);
			else text = text.substring(0, 5);
			currentChannel.currentDay.delete(convertString(text));
			
			removePanel.removeAll();
			details.removeAll();
			infoPanel.removeAll();
			infoPanel.add(info);
			info.setText("<html>Schedule: <br><br>"+currentChannel.currentDay.displayList()+"<br></html>");
			frame.repaint();
			noOfItems--;
		}
		else if (e.getSource() == view) {
			details.removeAll();
			details.add(typeSelect);
			details.add(viewSelect);
			buttons.removeAll();
			infoPanel.removeAll();
			infoPanel.add(info);
			info.setText("Please select a programme");
			infoPanel.add(rent);
			rent.setVisible(false);
			resetDays();
			channel2.setText("Channel Two");
			channel1.setText("Channel One");
			frame.repaint();			
		}
		else if (e.getSource() == viewSelect) {
			Object title = viewSelect.getSelectedItem();
			int item = 0;
			if (title instanceof String) {
				for (int i=0; i<dbase.length; i++) {
					if (title == dbase[i].getTitle())
						item = i;
				}
			}
			info.setText(dbase[item].info());
			rent.setVisible(true);
			rent.setSelected(dbase[item].getRentable());
			trailerDuration.setSelectedIndex(dbase[item].trailerDuration);
		}
		else if (e.getSource() == typeSelect) {

			viewSelect.removeAllItems();
			if (typeSelect.getSelectedItem() == "All") {
				for (int i=0; i<dbase.length; i++)
					viewSelect.addItem(dbase[i].getTitle());				
			}
			else {	
				for (int i=0; i<dbase.length; i++) {
					if (dbase[i].type == typeSelect.getSelectedItem()) {
						viewSelect.addItem(dbase[i].getTitle());
					}					
				}			
			}
			frame.repaint();
		}
		else if(e.getSource() == rent) {
			int item = viewSelect.getSelectedIndex();
			if (rent.isSelected()) {
				dbase[item].setRentable(true);
			}
			else if (!rent.isSelected()) {
				dbase[item].setRentable(false);
			}
		}
		else if (e.getSource() == rentable) {
			details.removeAll();
			buttons.removeAll();
			infoPanel.removeAll();
			infoPanel.add(info);
			resetDays();
			channel2.setText("Channel Two");
			channel1.setText("Channel One");
			viewSelect.removeAllItems();
			int n=0;
			for (int i=0; i<dbase.length; i++) {
				if (dbase[i].getRentable()) {
					viewSelect.addItem(dbase[i].getTitle());
					n++;
				}
			}
			if (n!=0) {					
				details.add(trailerDuration);
				details.add(viewSelect);
				info.setText("<html>Rentable Programmes.<br>Please select a programme.</html>");
			}
			else {
				info.setText("<html>No Rentable Programmes Found!<br><br>You Can Set Programmes Rentable In View Information Menu.</html>");
			}
			infoPanel.updateUI();				
			frame.repaint();
		}
		else if (e.getSource() == trailerDuration) {
			
			Object title = viewSelect.getSelectedItem();
			int item = 0;
			if (title instanceof String) {
				for (int i=0; i<dbase.length; i++) {
					if (title == dbase[i].getTitle())
						item = i;
				}
			}
			
			if (trailerDuration.getSelectedIndex()>-1)
				dbase[item].trailerDuration = trailerDuration.getSelectedIndex();
		}
		else if (e.getSource() == exit) {	//Exit
			System.exit(0);
		}
	}
	/**
	 * Converts time from HH:MM format to minutes
	 * 
	 * @param x String of HH:MM format
	 * @return	Time in minutes
	 */
	public int convertString(String x) {
			
		int hours = 0, minutes = 0;
		   
		if (x.length()==5) {
			hours=Integer.parseInt(x.substring(0,2));
			minutes=Integer.parseInt(x.substring(3,5));
		}
		   
		else if (x.length()==4) {
			hours=Integer.parseInt(x.substring(0,1));
			minutes=Integer.parseInt(x.substring(2,4));
		}
			
		int time=(hours*60)+minutes;
		return(time);
	}
	/**
	 * Resets the text on days buttons
	 */
	public void resetDays() {
		
		monday.setText("Monday");
		tuesday.setText("Tuesday");
		wednesday.setText("Wednesday");
		thursday.setText("Thursday");
		friday.setText("Friday");
		saturday.setText("Saturday");
		sunday.setText("Sunday");
	}
	/**
	 * Clean the GUI, remove unnecessary elements
	 * 
	 * @param i	Case id (different behaviours)
	 */
	public void clean(int i) {
		switch (i) {
		
			case 1: {
				details.removeAll();
				details.updateUI();
				infoPanel.removeAll();
				infoPanel.add(info);
				infoPanel.updateUI();
				buttons.removeAll();
				buttons.updateUI();
				resetDays();
			}
			
			case 2: {
				details.removeAll();
				details.updateUI();
				infoPanel.removeAll();
				infoPanel.add(info);
				infoPanel.updateUI();
				resetDays();
				
			}
			
			}
		
	}

}
