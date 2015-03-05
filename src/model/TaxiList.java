package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import exceptions.RegNbFormatException;

/**
 * Class regrouping taxis into a list
 * @author pgm1
 * @version 22/02/2015
 */
public class TaxiList {
	/** List of taxis **/
	private TreeSet<Taxi> taxiList = new TreeSet<Taxi>();
	/** Errors **/
	private static final String ERROR_READING = "Error during reading process: ";
	private static final String ERROR_NB_ARGUMENTS = "Input line should be 'registration number, nb of seats'";
	
	/**
	 * Constructor
	 * @param manager Manager
	 */
	public TaxiList(){	
		this.taxiList = new TreeSet<Taxi>();
	}
	
	/**
	 * Get the taxi list
	 * @return the taxi list
	 */
	public TreeSet<Taxi> getTaxiList(){
		return this.taxiList;
	}
	
	/**
	 * Set the taxi list
	 * @param taxiList the taxi list to set
	 */
	public void setTaxiList(TreeSet<Taxi> taxiList){
		this.taxiList = taxiList;
	}
	
	/**
	 * Get the number of taxis in the list
	 * @return number of taxis
	 */
	public int getNbTaxi() {
		return this.taxiList.size();
	}
	
	/**
	 * Get the taxi from his registration number
	 * @param regNb the registration number of the taxi to get
	 * @return the taxi with the registration number corresponding
	 */
	public Taxi getTaxiByRegNb(String regNb){
		
		Taxi taxi = null;
		if(regNb != null){
			Iterator<Taxi> taxiIterator = taxiList.iterator();
			while(taxiIterator.hasNext()){
				Taxi currentTaxi = taxiIterator.next();
				if(regNb.equals(currentTaxi.getRegNb())){
					taxi = currentTaxi;
					break;
				}
			}			
		}
		return taxi;
	}
	
	/**
	 * Add a new taxi
	 * @param t Taxi to add
	 * @return 1 success - 0 fail
	 */
	public boolean addTaxi(Taxi t) {
		return t != null ? taxiList.add(t) : false;	
	}
	
	/**
	 * Processes line, extracts data, creates taxi object
	 * and adds to list.
	 * @param line the line to be processed
	 */
	private void processLine(String line){

		String [] parts = line.split(",");
		if(parts.length == 2){
			String regNb = parts[0].trim();
			int nbOfSeats = Integer.parseInt(parts[1].trim());
	
			try {
				//create Taxi object and add to the list
				Taxi t = new Taxi(regNb, nbOfSeats);
				this.addTaxi(t); 
			} catch (RegNbFormatException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println(ERROR_READING + ERROR_NB_ARGUMENTS);
		}
	}
	
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 */
	@SuppressWarnings("resource")
	public void readFile(String fileName) throws FileNotFoundException{
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine()) {
			//read first line and process it
			String inputLine = scanner.nextLine(); 
			if (inputLine.length() != 0) {//ignored if blank line
				processLine(inputLine);		
			}	
		}
	}
	
	/**
	 * Remove a taxi from the list
	 * @param regNb the registration number of the taxi to remove
	 * @return 1 success - 0 fail
	 */
	public boolean removeTaxiByRegNb(String regNb){
		boolean success = false;
		Taxi taxiToRem = this.getTaxiByRegNb(regNb);
		if(taxiToRem != null){
			 success = taxiList.remove(taxiToRem);
		}
		return success;
	}
	
	/**
	 * Get all taxis with the number of passengers that it can carry
	 * @return all taxis with the number of passengers that it can carry
	 */
	public String getAllTaxis(){
		String allTaxis = "LIST TAXIS \n";	
		Iterator<Taxi> i = taxiList.iterator();
		//Go through the arrayList
		while (i.hasNext()) {
			//Get current taxi
			Taxi currentTaxi = i.next();
			//Add the registration number
			allTaxis += currentTaxi.getRegNb() + "\n";
			//Add the number of passenger that it can carry
			String nbPassenger ="";
			if (currentTaxi.getNbOfSeats() == 1) {
				nbPassenger = "1 person";
			} else {
				nbPassenger = currentTaxi.getNbOfSeats() + " people";
			}
			allTaxis += nbPassenger + "\n \n";
		}
		return allTaxis;
	}
	
	
}

