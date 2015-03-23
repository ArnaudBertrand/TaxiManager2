package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Taxi;
import exceptions.RegNbFormatException;

/**
 * Generate taxis
 */
public class TaxiGenerator {
	/** Taxi generator instance **/
	private static TaxiGenerator instance = new TaxiGenerator();
	/** List of cities where taxi can be registred **/
	private List<String> cityList;
	
	/**
	 * Constructor
	 */
	private TaxiGenerator(){
		// Initialise city list
		cityList = new ArrayList<String>();
		initCities();
	}
	
	/**
	 * Get instance of Taxi generator
	 * @return instance
	 */
	public static TaxiGenerator getInstance(){
		return instance;
	}
	
	/**
	 * Generate a taxi
	 * @return taxi generated
	 */
	public synchronized Taxi generate(){
		Taxi t = null;
		// Try to create a Taxi without getting error
		while(t == null){
			try {
				// Get random registration
				String reg = pickRandomCity() + "-" + pickRandomReg();
				// Create taxi
				t = new Taxi(reg,pickRandomNbSeat());
			} catch (RegNbFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		return t;
	}
	/**
	 * Generate a random city for the reg number
	 * @return random city
	 */
	private String pickRandomCity(){
		// Shuffle list
		Collections.shuffle(cityList);
		// Get first item
		return cityList.get(0);
	}
	
	/**
	 * Generator random alphanumerical characters for reg number
	 * @return random alphanumerical characters
	 */
	private String pickRandomReg(){
		String reg = "";
		Random r = new Random();
		// 6 Chars
		for(int i = 0; i<6; i++){
			int j = r.nextInt(36);
			if(j<10){
				// Number
				reg += (char)(j + '0');
			} else {
				// Char
				reg += (char)(j - 10 + 'A');
			}
		}
		return reg;
	}
	
	/**
	 * Generate a random number of seats
	 * @return random number of seats
	 */
	private int pickRandomNbSeat() {
	    Random rand = new Random();
	    // Random number of seats between 4 and 6
	    int randomNum = rand.nextInt(3) + 4;
	    return randomNum;
	}
	
	/**
	 * Initialise cities
	 */
	private void initCities(){
		cityList.add("LON");
		cityList.add("OXF");
		cityList.add("MAN");
		cityList.add("LIV");
	}
}