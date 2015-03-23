package model;

import generator.TaxiGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

/**
 * Class regrouping taxis into a list
 * @author pgm1
 * @version 22/02/2015
 */
public class TaxiList {
	/** List of taxis **/
	private ArrayList<Taxi> taxiList;
	
	/**
	 * Constructor
	 * @param taxi list
	 */
	public TaxiList(){	
		this.taxiList = new ArrayList<Taxi>();
	}
	
	/**
	 * Get the taxi list
	 * @return the taxi list
	 */
	public ArrayList<Taxi> getTaxiList(){
		return this.taxiList;
	}
	
	/**
	 * Set the taxi list
	 * @param taxiList the taxi list to set
	 */
	public void setTaxiList(ArrayList<Taxi> taxiList){
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
	 * Init taxis
	 */
	public void initTaxis(){
		int limit = 0;
		while(!(limit>0)){
			try{
				limit = Integer.parseInt(JOptionPane.showInputDialog("Number of taxi"));				
			} catch (NumberFormatException e){
				System.out.println("Please enter a number");
			}
		}
		while(limit>0){
			this.addTaxi(TaxiGenerator.getInstance().generate());
			limit--;
		}
	}
	
	/**
	 * Remove a taxi from the list
	 * @param regNb the registration number of the taxi to remove
	 * @return 1 success - 0 fail
	 */
	public boolean removeTaxiByRegNb(String regNb){
		Taxi taxiToRem = this.getTaxiByRegNb(regNb);
		return taxiToRem != null ? taxiList.remove(taxiToRem) : false;
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

	public Taxi pop() {
		Taxi t = null;
		if(taxiList.size() > 0){
			t = taxiList.get(0);
			taxiList.remove(0);			
		}
		return t;
	}

	/**
	 * Get taxi list by regulation number
	 * @return string of taxi list
	 * */
	public String getTaxiListByRegNb() {
		StringBuilder sb = new StringBuilder("");
		Iterator<Taxi> it = taxiList.iterator();
		while (it.hasNext()) {
			sb.append(it.next().toString() + "\n");
		}
		return sb.toString();
	}	
}

