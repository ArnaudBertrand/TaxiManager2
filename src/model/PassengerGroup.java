package model;

public class PassengerGroup {
	
	private String destination;
	private int nbPeople;
	
	public PassengerGroup(String destination, int nbPeople) {
		this.destination = destination;
		this.nbPeople = nbPeople;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNbPeople() {
		return nbPeople;
	}

	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}

	//Method toString called by PassengerGUI.createList
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
