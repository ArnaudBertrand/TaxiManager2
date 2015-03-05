package main;

import java.io.FileNotFoundException;

import views.PassengersGUI;
import views.TaxiGUI;
import model.PassengerGroupsList;
import model.TaxiList;
import controller.PassengersController;
import controller.TaxiController;


public class Main {

	// Path for input files
	private static final String PATH_READ_PASSENGERGROUPS_DETAILS = "PassengerGroupsDetails.txt";
	private static final String PATH_READ_TAXIS_DETAILS = "TaxiDetails.txt";

	public static void main(String[] args) {
		PassengerGroupsList modelPassenger = new PassengerGroupsList();
		TaxiList modelTaxi = new TaxiList();
		try {
		// Read the file containing the passenger groups
			modelPassenger.readFile(PATH_READ_PASSENGERGROUPS_DETAILS);
			modelTaxi.readFile(PATH_READ_TAXIS_DETAILS);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		//PassengersGUI viewPassenger = new PassengersGUI(modelPassenger);
		//PassengersController passengerController = new PassengersController(viewPassenger, modelPassenger);
	
		TaxiGUI viewTaxi = new TaxiGUI(modelTaxi);
		TaxiController taxiController = new TaxiController(viewTaxi, modelTaxi);
		
	}

}
