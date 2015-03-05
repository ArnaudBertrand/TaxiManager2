package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.TaxiList;
import views.TaxiGUI;

public class TaxiController {

	// GUI to display the number of taxis wanted (by setting a number)
	private TaxiGUI view;
	// List of taxis
	private TaxiList model;

	public TaxiController(TaxiGUI view, TaxiList model) {
		this.view = view;
		this.model = model;
		// specify the listener for the view
		view.addSetListener(new SetListener());
	}

	// inner class SetListener responds 
	// when user sets the number of taxis that they want
	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int nbTaxis = Integer.parseInt(view.getNbOfSeats());
			view.setNbTaxis(nbTaxis);
		}
	}

}
