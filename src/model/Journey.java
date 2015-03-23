package model;

public class Journey {

	/** Instanciate variables **/
	private Taxi taxi;
	private PassengerGroup group;
	
	/** Errors **/
	private final static String ERROR_NULL_TAXI_DEST = "Taxi or destination cannot be null";

	/**
	 * Constructor
	 * 
	 * @param taxi
	 * @param Passenger group
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException 
	 * */
	public Journey(Taxi taxi, PassengerGroup group) throws NullPointerException, IllegalArgumentException {
		//Exception if the taxi or passenger group is null
		if(taxi == null || group == null){
			throw new NullPointerException(ERROR_NULL_TAXI_DEST);
		}
		this.taxi = taxi;
		this.group = group;
	}

	/**
	 * Get the taxi object
	 * @return the taxi that made the journey
	 * */
	public Taxi getTaxi() {
		return this.taxi;
	}

	/**
	 * Set the taxi object
	 * @param taxi object to set
	 * */
	public void setTaxi(Taxi t) {
		this.taxi = t;
	}

	/**
	 * Get the group of passenger
	 * @return the passenger group object for the journey
	 * */
	public PassengerGroup getPassengerGroup() {
		return this.group;
	}

	/**
	 * Set the group of passenger
	 * @param group of passenger object to set
	 * */
	public void setPassengerGroup(PassengerGroup pg) {
		this.group = pg;
	}
}
