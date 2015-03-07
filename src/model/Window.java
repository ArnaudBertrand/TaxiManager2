package model;

import java.util.Observable;

public class Window extends Observable implements Runnable{
	private int kioskID;
	private Journey currentJourney;
	private Manager manager;
	
	public Window(int kioskID, Manager manager){
		this.kioskID = kioskID;
		this.manager = manager;
		this.currentJourney = null;
	}

	public void run() {
		while (!manager.isFinished()){
			currentJourney = manager.getNewJourney();
			if(currentJourney != null){
				try {
					System.out.println("New journey: " + currentJourney.getTaxi().getRegNb() + " - " 
							+ currentJourney.getPassengerGroup().getDestination() + " - " 
							+ currentJourney.getPassengerGroup().getNbPeople());
					//update view display
					setChanged();
					notifyObservers();
			    	clearChanged();
	
					// Random time before 1 and 5 secs
					Thread.sleep((long) (1000*(5 +Math.random()*5)));				
				} catch (InterruptedException e) {
					System.out.println("Kiosk " + kioskID + "  Interrupted");
				}
			}
		}
	}

	public int getKioskID() {
		return kioskID;
	}
	
	public PassengerGroup getPassengerGroup(){
		return currentJourney.getPassengerGroup();
	}
	
	public Taxi getTaxi(){
		return currentJourney.getTaxi();
	}
	
	public Journey getCurrentJourney(){
		return currentJourney;
	}
}
