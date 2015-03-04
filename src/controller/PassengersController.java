package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.PassengerGroup;
import model.PassengerGroupsList;
import views.PassengersGUI;

//Handles interaction with users
//calls view and model as needed

public class PassengersController {

	// GUI to display the passenger groups wanted (by setting a number)
	private PassengersGUI view;
	// List of passenger groups
	private PassengerGroupsList model;

	public PassengersController(PassengersGUI view, PassengerGroupsList model) {
		this.view = view;
		this.model = model;
		// specify the listener for the view
		view.addSetListener(new SetListener());
	}

	// inner class SetListener responds when user sets the number of groups
	// wanted
	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int nbPassengerGroups = Integer.parseInt(view.getNbPG());
			view.setNbPG(nbPassengerGroups);
		}
	}
}
