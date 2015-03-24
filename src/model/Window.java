package model;

import java.util.Observable;

import main.Log;

/**
 * Class for window model
 */
public class Window extends Observable implements Runnable{
	/** Id of window **/
	private int windowId;
	/** Current journey of the window **/
	private Journey currentJourney;
	/** Manager of the window **/
	private Manager manager;
	/** If window is paused **/
	public boolean paused;
	
	/**
	 * Constructor
	 * @param windowId window id
	 * @param manager manager of window
	 */
	public Window(int windowId, Manager manager){
		// Initiate variables
		this.windowId = windowId;
		this.manager = manager;
		this.currentJourney = null;
	}

	/**
	 * Method run of window thread
	 */
	public void run() {
		// Stop when manager is finished
		while (!manager.isFinished()){
			try {
				// Check if window is not paused
				if(!paused){
					// Get a new journey
					currentJourney = manager.getNewJourney();
					
					// If we get a new journey
					if(currentJourney != null){
						// Log the journey
						String log = String.format("W" + windowId + ": Destination: %-20s Passengers: " 
								+ currentJourney.getPassengerGroup().getNbPeople() + " Taxi: %-10s", 
								currentJourney.getPassengerGroup().getDestination(), currentJourney.getTaxi().getRegNb());
						Log.getInstance().log(log);
						
						// Update view display
						setChanged();
						notifyObservers();
				    	clearChanged();
						
						// Pause thread for random time
						Thread.sleep((long) ((1000/manager.getSpeed())*(5 +Math.random()*5)));				
					}
				} else {
					// Update view display
			    	currentJourney = null;
					setChanged();
					notifyObservers();
			    	clearChanged();
					// Pause thread
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				System.out.println("Kiosk " + windowId + "  Interrupted");
			}
		}
		
		// Clear window and update view
    	currentJourney = null;
		setChanged();
		notifyObservers();
    	clearChanged();
	}

	/**
	 * Get the window id
	 * @return window id
	 * */
	public int getWindowID() {
		return windowId;
	}
	
	/**
	 * Get the passenger group
	 * @return the passenger group
	 * */
	public PassengerGroup getPassengerGroup(){
		return currentJourney.getPassengerGroup();
	}
	
	/**
	 * Get the taxi
	 * @return the current taxi
	 * */
	public Taxi getTaxi(){
		return currentJourney.getTaxi();
	}
	
	
	/**
	 * Get the journey
	 * @return the current journey
	 * */
	public Journey getCurrentJourney(){
		return currentJourney;
	}
	
	/**
	 * Is the application in paused ?
	 * @param paused boolean
	 * */
	public boolean isPaused(){
		return paused;
	}

	/**
	 * Set the application to pause
	 * @param boolean
	 * */
	public void setPaused(boolean b) {
		this.paused = b;
	}	
}
