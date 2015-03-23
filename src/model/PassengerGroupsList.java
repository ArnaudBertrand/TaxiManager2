package model;

import generator.PassengerGroupGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class PassengerGroupsList {

	/** Instanciate variables **/
	private ArrayList<PassengerGroup> passengerGroupsList = new ArrayList<PassengerGroup>();

	/**
	 * Constructor
	 * @param passenger group list
	 * */
	public PassengerGroupsList() {
		this.passengerGroupsList = new ArrayList<PassengerGroup>();
	}

	/**
	 * Get the passenger group list
	 * @return passenger group list
	 * */
	public ArrayList<PassengerGroup> getPassengerGroupsList() {
		return this.passengerGroupsList;
	}

	/**
	 * set the passenger group list
	 * @param passenger group list
	 * */
	public void setPassengerGroupsList(
			ArrayList<PassengerGroup> passengerGroupsList) {
		this.passengerGroupsList = passengerGroupsList;
	}

	/**
	 * Initialize the number of passenger groups
	 */
	public void initPassengerGroups() {
		// Set max number of passenger groups
		int limit = 0;
		// Ask the number of passenger groups
		while(!(limit>0)){
			try{
				limit = Integer.parseInt(JOptionPane.showInputDialog("Number of passengers"));				
			} catch (NumberFormatException e){
				System.out.println("Please enter a number");
			}
		}
		
		//add the passenger group from the passenger group generator
		while (limit > 0) {
			this.addPassengerGroups(PassengerGroupGenerator.getInstance().generate());
			limit--;
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
	
	/**
	 * Return all the passenger groups as a string
	 * @return string of passenger groups
	 * */
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

	/**
	 * 
	 * */
	public PassengerGroup pop(int nbSeats) {
		PassengerGroup pg = null;
		for(PassengerGroup pgroup : passengerGroupsList){
			if(pgroup.getNbPeople() <= nbSeats){
				pg = pgroup;
				passengerGroupsList.remove(pgroup);
				break;
			}
		}
		return pg;
	}

	/**
	 * Get the size of the passenger list
	 * @return size of passenger list
	 * */
	public int getSize() {
		return passengerGroupsList.size();
	}
	
	/**
	 * Overwrite the method toString
	 * @return string of passenger list
	 * */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Iterator<PassengerGroup> it = passengerGroupsList.iterator();
		while (it.hasNext()) {
			sb.append(it.next().toString() + "\n\n");
		}
		return sb.toString();
	}
}
