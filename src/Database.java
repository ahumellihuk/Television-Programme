/**
 * A hard-coded database of all available programmes
 * 
 * @author Dmitri Samoilov
 *
 */
public class Database {
	/**
	 * Creates instances of programme and fills an array with them
	 * 
	 * @param dbase An array to hold all the programmes
	 */
	static  void initialise(Programme [] dbase) {
		dbase[0] = new Comedy("Ace Ventura: Pet Detective", 90, "Tom Shadyac", "Jim Carrey, Courteney Cox, Sean Young", "A goofy " +
				"detective specializing in animals goes<br> in search of a missing dolphin mascot of a football team. ", 1994);
		dbase[1] = new Music_Video("Dance Music Videos", 60, "Alexandra Stan; Afrojack feat. Eva Simons; Cascada;" +
				"<br> Selena Gomez; Jay-Z; Taio Cruz; Lady Gaga; Katy Perry", 2011);
		dbase[2] = new Still_Picture("Evening Menu",30);	
		dbase[3] = new Video("Fire Safety",30);
		dbase[4] = new Film("Inception", 150, "Christopher Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "In a world where" +
				" technology exists to enter the human mind<br> through dream invasion, a highly skilled thief is given a final chance at redemption" +
				" which<br> involves executing his toughest job to date: Inception.", 2010);
		dbase[5] = new Film("Limitless", 120, "Neil Burger", "Bradley Cooper, Anna Friel and Abbie Cornish", 
				"A writer discovers a top-secret drug which<br> bestows him with super human abilities.", 2011);
		dbase[6] = new Still_Picture("Lunch Menu",30);
		dbase[7] = new Still_Picture("Morning Menu",30);
		dbase[8] = new Comedy("New Year's Eve", 120, "Garry Marshall", "Sarah Jessica Parker, Jessica Biel and Ashton Kutcher", 
				"The lives of several couples and singles in New York<br> intertwine over the course of New Year's Eve. ", 2011);
		dbase[9] = new Film("Pulp Fiction", 150, "Quentin Tarantino", "John Travolta, Uma Thurman, Samuel L. Jackson", "The lives of two mob hit men, " +
				"a boxer, a gangster's wife,<br> and a pair of diner bandits intertwine in four tales of violence and redemption.", 1994);
		dbase[10] = new Video("Something",30);		
		dbase[11] = new Drama("The Dark Knight", 150, "Christopher Nolan", "Christian Bale, Heath Ledger and Aaron Eckhart", 
				"Batman, Gordon and Harvey Dent are forced to deal with the chaos unleashed<br> by a terrorist mastermind " +
				"known only as the Joker, as he drives each of them to their limits. ", 2008);
		dbase[12] = new Drama("The Girl with the Dragon Tattoo", 150, "David Fincher", "Daniel Craig, Rooney Mara and Christopher Plummer", 
				"Journalist Mikael Blomkvist is aided in his search for a woman who has been<br> missing for forty years by " +
				"Lisbeth Salander, a young computer hacker. ", 2011);
		dbase[13] = new Video("Tourist Attractions",30);
		

		
		
		

		
		
	}
}