package main;

import java.io.FileNotFoundException;

import views.PassengersGUI;
import model.PassengerGroupsList;
import controller.PassengersController;


public class Main {

	// Path for input files
	private static final String PATH_READ_PASSENGERGROUPS_DETAILS = "PassengerGroupsDetails.txt";

	public static void main(String[] args) {
		PassengerGroupsList modelPassenger = new PassengerGroupsList();
		try {
		// Read the file containing the passenger groups
			modelPassenger.readFile(PATH_READ_PASSENGERGROUPS_DETAILS);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		PassengersGUI viewPassenger = new PassengersGUI(modelPassenger);
		PassengersController passengerController = new PassengersController(viewPassenger, modelPassenger);
	}

}
