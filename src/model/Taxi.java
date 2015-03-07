package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.RegNbFormatException;

public class Taxi implements Comparable<Taxi> {
	/** Registration number of the taxi **/
	private String regNb;
	/** Number of seat in the taxi **/
	private int nbOfSeats;
	/** Regex **/
	private static final String REGEX_REG_NB = "[A-Z]{3}-[A-Z0-9]{6}";
	/** Errors **/
	private static final String ERROR_NULL_ARGUMENT = "Driver name and reg number should not be null";
	
	/**
	 * Constructor
	 * @param driverName name of the driver of the taxi
	 * @param regNb registration number or the taxi
	 * @throws RegNbFormatException 
	 */
	public Taxi(String regNb, int nbOfSeats) throws RegNbFormatException{	
		if(nbOfSeats == 0 || regNb == null){
			throw new NullPointerException(ERROR_NULL_ARGUMENT);
		}
		Pattern regex = Pattern.compile(REGEX_REG_NB);
		Matcher match = regex.matcher(regNb);
		if(regNb.length() != 10 || !match.find()){
			throw new RegNbFormatException(regNb);
		}
		this.regNb = regNb;	
		this.nbOfSeats = nbOfSeats;
	}
	
	/**
	 * Get the registration number of the taxi
	 * @return registration number of the taxi
	 */
	public String getRegNb(){
		return this.regNb;
	}
	
	/**
	 * Set the registration number of the taxi
	 * @param regNb registration number of the taxi to set
	 */
	public void setRegNb(String regNb){
		this.regNb = regNb;
	} 

	/**
	 * Get the number of seat in the taxi
	 * @return number of seat in the taxi
	 */
	public int getNbOfSeats(){
		return this.nbOfSeats;
	}
	
	/**
	 * Set the number of seat in the taxi
	 * @param nbOfSeats number of seat in the taxi to set
	 */
	public void setNbOfSeats(int nbOfSeats){
		this.nbOfSeats = nbOfSeats;
	}
	
	/**
	 * Equals method
	 */
	public boolean equals(Object other){
		boolean eq = false;
		if(other != null && other instanceof Taxi){
		    Taxi t = (Taxi) other;
			eq = this.getRegNb().equals(t.getRegNb());
		}
		return eq;
	}
	
	/**
	 * CompareTo method
	 */
	public int compareTo(Taxi other) {
		return regNb.compareTo(other.regNb);
	}
	
	/**
	 * ToString method
	 */
	public String toString() {
		return regNb;
	}
}
