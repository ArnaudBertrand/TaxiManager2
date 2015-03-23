package model;

import java.util.Observable;

import main.Log;

public class Manager extends Observable implements Runnable{
	// Initialise variables
	private WindowList kioskList;
	private PassengerGroupsList passengerGroupsList;
	private TaxiList taxiList;
	private Thread [] kiosks;
	private boolean finished;
	private int speed;
	private static final int SPEED_MAX = 5;
	private static final int SPEED_MIN = 1;
	
	public Manager(){
		passengerGroupsList = new PassengerGroupsList();
		taxiList = new TaxiList();
		kioskList = new WindowList();
		speed = 1;
		
		// Create kiosks
		for (int i = 1; i <= 3; i++) {
			Window k = new Window (i, this);
			kioskList.add(k);
		}
		
		// Init taxis and passenger groups
		passengerGroupsList.initPassengerGroups();
		taxiList.initTaxis();
			
	}
	
	public void run(){
		
		kiosks = new Thread[kioskList.getSize()];
    	for (int ki = 0; ki < kioskList.getSize(); ki++)
    	{
    		kiosks[ki] = new Thread(kioskList.get(ki));
    		kiosks[ki].start();
    	}				
	}

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
	
	public boolean isFinished(){
		return finished;
	}
	
	public WindowList getWindowsList() {
		return kioskList;
	}

	public PassengerGroupsList getPassengerGroupList() {
		return passengerGroupsList;
	}

	public TaxiList getTaxiList() {
		return taxiList;
	}

	public void setFinished(boolean b) {
		this.finished = b;
		if(b){
			Log.getInstance().log("Application stopped");
			Log.getInstance().export("logs.txt");			
		}
	}
	
	public int increaseSpeed(){
		return this.speed<SPEED_MAX ? ++this.speed : this.speed;
	}
	
	public int decreaseSpeed(){
		return this.speed>SPEED_MIN ? --this.speed : this.speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
}
