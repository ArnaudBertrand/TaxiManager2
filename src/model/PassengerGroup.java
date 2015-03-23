package model;

public class PassengerGroup {
	
	private String destination;
	private int nbPeople;
	
	/**
	 * Constructor
	 * @param destination
	 * @param number of people
	 * */
	public PassengerGroup(String destination, int nbPeople) {
		this.destination = destination;
		this.nbPeople = nbPeople;
	}

	/**
	 * Get the destination
	 * @return destination
	 * */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * set the destination
	 * @param destination
	 * */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * get the number of passenger
	 * @return nb of people
	 * */
	public int getNbPeople() {
		return nbPeople;
	}

	/**
	 * set the number of passenger
	 * @param number of passenger
	 * */
	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}
	
	/**
	 * Overwrite the method toString
	 * @return passenger group to string
	 * */
	@Override
	public String toString() {
		String PG = "";
		//Add the destination
		PG = destination + "\n";
		//Add the number of people
		String nbPeople ="";
		if (this.getNbPeople() == 1) {
			nbPeople = "1 person";
		} else {
			nbPeople = this.getNbPeople() + " people";
		}
		PG += nbPeople;
	
	return PG;
	}

}
