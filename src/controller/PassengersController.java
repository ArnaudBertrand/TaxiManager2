package controller;

import generator.PassengerGroupGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PassengerGroupsList;
import views.PassengersGUI;
/**
 * Controller class for passengers
 */
public class PassengersController {
	/** Passenger group model **/
    private PassengerGroupsList pgl;
    /** Passenger group view **/
	private PassengersGUI view;

	/**
	 * Constructor
	 * @param pgl passenger group model
	 * @param v passenger group view
	 */
	public PassengersController(PassengerGroupsList pgl, PassengersGUI v) {
		this.pgl = pgl;
		view = v;
		// Add action listener
		view.addNewPassengersGroupListener(new NewPasssengersGroupController());
	}
	
	/**
	 * Action listener to add new passenger group
	 */
    class NewPasssengersGroupController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        	// Add new passenger group
			pgl.addPassengerGroups(PassengerGroupGenerator.getInstance().generate());
        }
    }

}
