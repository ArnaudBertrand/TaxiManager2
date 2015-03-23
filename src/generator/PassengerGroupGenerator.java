package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.PassengerGroup;

public class PassengerGroupGenerator {
	private static PassengerGroupGenerator instance = new PassengerGroupGenerator();
	private List<String> destList;
	
	private PassengerGroupGenerator (){
		destList = new ArrayList<String>();
		initDestinations();
	}
	
	public static PassengerGroupGenerator getInstance(){
		return instance;
	}
	
	public synchronized PassengerGroup generate(){
		return new PassengerGroup(pickRandomDest(),pickRandomPassengerNumber());
	}
	
	private String pickRandomDest(){
		Collections.shuffle(destList);
		return destList.get(0);
	}
	
	private int pickRandomPassengerNumber() {
	    Random rand = new Random();
	    // Random passengers between 1 and 6
	    int randomNum = rand.nextInt(6) + 1;
	    return randomNum;
	}
	
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