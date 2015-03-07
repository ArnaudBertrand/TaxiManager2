package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class PassengerGroupsList {

	
	private ArrayList<PassengerGroup> passengerGroupsList = new ArrayList<PassengerGroup>();

	public PassengerGroupsList() {
		this.passengerGroupsList = new ArrayList<PassengerGroup>();
	}

	public ArrayList<PassengerGroup> getPassengerGroupsList() {
		return this.passengerGroupsList;
	}

	public void setPassengerGroupsList(
			ArrayList<PassengerGroup> passengerGroupsList) {
		this.passengerGroupsList = passengerGroupsList;
	}

	/**
	 * Read a file
	 * 
	 * @param fileName
	 *            path of the file to read
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public void readFile(String fileName) throws FileNotFoundException {
		// Set max number of taxi
		int limit = 0;
		while(!(limit>0)){
			try{
				limit = Integer.parseInt(JOptionPane.showInputDialog("Number of passengers"));				
			} catch (NumberFormatException e){
				System.out.println("Please enter a number");
			}
		}
		
		// Read file
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine() && limit > 0) {
			// read first line and process it
			String inputLine = scanner.nextLine();
			if (inputLine.length() != 0) {// ignored if blank line
				processLine(inputLine);
			}
			limit--;
		}
	}

	private void processLine(String line) {

		String[] parts = line.split(",");
		if (parts.length == 2) {
			String dest = parts[0].trim();
			int nbPerson = Integer.parseInt(parts[1].trim());

			this.addPassengerGroups(new PassengerGroup(dest, nbPerson));
		} else {
			System.out.println("Error nb of arguments PassengerGroups");
		}
	}

	/**
	 * Add a new passenger group
	 * 
	 * @param pg
	 *            passenger group to add
	 */
	public boolean addPassengerGroups(PassengerGroup pg) {
		return pg != null ? passengerGroupsList.add(pg) : false;
	}
	
	public String getAllPassengerGroups(){
		String allPG = "PASSENGER GROUPS \n";	
		Iterator<PassengerGroup> i = passengerGroupsList.iterator();
		//Go through the arrayList
		while (i.hasNext()) {
			//Get current passenger group
			PassengerGroup currentPG = i.next();
			//Add the destination
			allPG += currentPG.getDestination() + "\n";
			//Add the number of people
			String nbPeople ="";
			if (currentPG.getNbPeople() == 1) {
				nbPeople = "1 person";
			} else {
				nbPeople = currentPG.getNbPeople() + " people";
			}
			allPG += nbPeople + "\n \n";
		}
		return allPG;
	}

	public PassengerGroup pop() {
		PassengerGroup pg = null;
		if(passengerGroupsList.size() > 0){
			pg = passengerGroupsList.get(0);			
			passengerGroupsList.remove(0);
		}
		return pg;
	}

	public int getSize() {
		return passengerGroupsList.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Iterator<PassengerGroup> it = passengerGroupsList.iterator();
		while (it.hasNext()) {
			sb.append(it.next().toString() + "\n\n");
		}
		return sb.toString();
	}
}
