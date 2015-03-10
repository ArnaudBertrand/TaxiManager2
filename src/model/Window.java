package model;

import java.util.Observable;

import main.Log;

public class Window extends Observable implements Runnable{
	private int kioskID;
	private Journey currentJourney;
	private Manager manager;
	public boolean paused;
	
	public Window(int kioskI, Manager manager){
		this.kioskID = kioskI;
		this.manager = manager;
		this.currentJourney = null;
	}

	public void run() {
		while (!manager.isFinished()){
			if(!paused){
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
	
	public boolean isPaused(){
		return paused;
	}

	public void setPaused(boolean b) {
		this.paused = b;
	}	
}
