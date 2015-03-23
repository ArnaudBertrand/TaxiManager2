package model;

import java.util.Observable;

import main.Log;

public class Window extends Observable implements Runnable{
	private int kioskID;
	private Journey currentJourney;
	private Manager manager;
	public boolean paused;
	
	/**
	 * Constructor
	 * @param kiosk id
	 * @param manager
	 * @param current journey
	 * */
	public Window(int kioskI, Manager manager){
		this.kioskID = kioskI;
		this.manager = manager;
		this.currentJourney = null;
	}

	/**
	 * Method run
	 * */
	public void run() {
		while (!manager.isFinished()){
			if(!paused){
				//Get a new journey
				currentJourney = manager.getNewJourney();
				if(currentJourney != null){
					try {
						// Log
						String log = String.format("W" + kioskID + ": Destination: %-20s Passengers: " 
								+ currentJourney.getPassengerGroup().getNbPeople() + " Taxi: %-10s", 
								currentJourney.getPassengerGroup().getDestination(), currentJourney.getTaxi().getRegNb());
						
						Log.getInstance().log(log);
						
						//update view display
						setChanged();
						notifyObservers();
				    	clearChanged();
		
						// Random time to wait according to speed
						Thread.sleep((long) ((1000/manager.getSpeed())*(5 +Math.random()*5)));				
					} catch (InterruptedException e) {
						System.out.println("Kiosk " + kioskID + "  Interrupted");
					}
				}				
			}
		}
	}

	/**
	 * Get the kiosk id
	 * @return kiosk id
	 * */
	public int getKioskID() {
		return kioskID;
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
