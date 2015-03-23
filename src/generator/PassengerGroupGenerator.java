package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.PassengerGroup;

/**
 * Generate passenger groups
 */
public class PassengerGroupGenerator {
	/** Instance of passenger group generator **/
	private static PassengerGroupGenerator instance = new PassengerGroupGenerator();
	/** List of destinations **/
	private List<String> destList;
	
	/**
	 * Constructor
	 */
	private PassengerGroupGenerator (){
		// Init destinations
		destList = new ArrayList<String>();
		initDestinations();
	}
	
	/**
	 * Get instance of passenger group generator
	 * @return instance
	 */
	public static PassengerGroupGenerator getInstance(){
		return instance;
	}
	
	/**
	 * Generate a passenger group
	 * @return random passenger group
	 */
	public synchronized PassengerGroup generate(){
		return new PassengerGroup(pickRandomDest(),pickRandomPassengerNumber());
	}
	
	/**
	 * Generate a random destination
	 * @return a random destination
	 */
	private String pickRandomDest(){
		// Shuffle list
		Collections.shuffle(destList);
		// Get the first destination
		return destList.get(0);
	}
	
	/**
	 * Generate a random number of passengers
	 * @return random number of passengers
	 */
	private int pickRandomPassengerNumber() {
	    Random rand = new Random();
	    // Random passengers between 1 and 6
	    int randomNum = rand.nextInt(6) + 1;
	    return randomNum;
	}
	
	/**
	 * Init destinations
	 */
	private void initDestinations(){
		destList.add("Edinburgh");
		destList.add("Liverpool");
		destList.add("Cambridge");
		destList.add("Birmingham");
		destList.add("Dover");
		destList.add("Kensington");
		destList.add("Palace");
		destList.add("Victoria Avenue");
		destList.add("Clissold Park");
		destList.add("Tower of London");
		destList.add("Victoria Park");
		destList.add("Greenford");
		destList.add("Royal Victoria");
		destList.add("Wimbledon");
		destList.add("Camberwell");
		destList.add("Holborn");
		destList.add("Marylebone");
		destList.add("London Bridge");
		destList.add("Old Street");
		destList.add("Saint Giles");
		destList.add("Essex Road");
		destList.add("Finsbury");
		destList.add("Moorgate");
		destList.add("Shadwell");
		destList.add("Cannon Street");
		destList.add("Walworth");
		destList.add("Forest Hill");
	}
}