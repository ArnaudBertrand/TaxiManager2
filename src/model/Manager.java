package model;

import java.util.Observable;

import main.Log;

/**
 * Class for manager model
 */
public class Manager extends Observable implements Runnable{
	/** List of windows **/
	private WindowList windowList;
	/** Passenger groups list **/
	private PassengerGroupsList passengerGroupsList;
	/** Taxi list **/
	private TaxiList taxiList;
	/** Window threads **/
	private Thread [] windows;
	/** Program finished or not **/
	private boolean finished;
	/** Current speed **/
	private int speed;
	/** Max speed **/
	private static final int SPEED_MAX = 5;
	/** Min speed **/
	private static final int SPEED_MIN = 1;
	
	/**
	 * Constructor
	 */
	public Manager(){
		// Initiate variables
		passengerGroupsList = new PassengerGroupsList(this);
		taxiList = new TaxiList(this);
		windowList = new WindowList();
		speed = 1;
		
		// Create kiosks
		for (int i = 1; i <= 3; i++) {
			Window k = new Window (i, this);
			windowList.add(k);
		}
		
		// Init taxis and passenger groups
		passengerGroupsList.initPassengerGroups();
		taxiList.initTaxis();
	}
	
	/**
	 * Run function for manager thread
	 */
	public void run(){
		// Launch thread for each window
		windows = new Thread[windowList.getSize()];
    	for (int ki = 0; ki < windowList.getSize(); ki++)
    	{
    		windows[ki] = new Thread(windowList.get(ki));
    		windows[ki].start();
    	}				
	}
	
	/**
	 * Get a new journey for a window
	 * @return journey
	 */
	public synchronized Journey getNewJourney() {
		Journey j = null;
		// While we do not have a journey
		while(j == null && !finished){
			// Check if we still have taxis and passengers groups, otherwise simulation ends
			if(taxiList.getNbTaxi() > 0 && passengerGroupsList.getSize() > 0){
				// Look at the new Taxi in the queue
				Taxi newTaxi = taxiList.pop();
				
				// Get the next
				PassengerGroup newGroup = passengerGroupsList.pop(newTaxi.getNbOfSeats());
				// If taxi can take a group associate the journey
				if(newGroup != null){
					j = new Journey(newTaxi,newGroup);
				}
				
				// Update view display
				setChanged();
				notifyObservers();
		    	clearChanged();
			} else {
				setFinished(true);
			}			
		}
		return j;
	}
	
	/**
	 * Is program finished
	 * @return true if program is finished
	 */
	public boolean isFinished(){
		return finished;
	}
	
	/**
	 * Get the windows list
	 * @return window list
	 */
	public WindowList getWindowsList() {
		return windowList;
	}

	/**
	 * Get passenger group list
	 * @return passenger group list
	 */
	public PassengerGroupsList getPassengerGroupList() {
		return passengerGroupsList;
	}

	/**
	 * Get taxi list
	 * @return taxi list
	 */
	public TaxiList getTaxiList() {
		return taxiList;
	}

	/**
	 * Stop the program
	 * @param b true if you want to stop the program
	 */
	public void setFinished(boolean b) {
		// Check if not already stopped
		if(!this.finished){
			this.finished = b;
			// If stop then export logs and notify
			if(b){
				Log.getInstance().log("Application stopped");
				Log.getInstance().export("logs.txt");
				
				// Update view display
				updateViews();
			}			
		}
	}
	
	/**
	 * Increase the speed of the simulation
	 * @return current speed
	 */
	public int increaseSpeed(){
		return this.speed<SPEED_MAX ? ++this.speed : this.speed;
	}
	
	/**
	 * Decrease the speed of the simulation
	 * @return current speed
	 */
	public int decreaseSpeed(){
		return this.speed>SPEED_MIN ? --this.speed : this.speed;
	}
	
	/**
	 * Get the current speed
	 * @return current speed
	 */
	public int getSpeed(){
		return this.speed;
	}

	public void updateViews() {
		setChanged();
		notifyObservers();
    	clearChanged();
	}
}
