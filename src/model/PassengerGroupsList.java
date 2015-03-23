package model;

import generator.PassengerGroupGenerator;

import java.util.ArrayList;
import java.util.Iterator;

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
	 */
	public void initPassengerGroups() {
		// Set max number of taxi
		int limit = 0;
		while(!(limit>0)){
			try{
				limit = Integer.parseInt(JOptionPane.showInputDialog("Number of passengers"));				
			} catch (NumberFormatException e){
				System.out.println("Please enter a number");
			}
		}
		
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
