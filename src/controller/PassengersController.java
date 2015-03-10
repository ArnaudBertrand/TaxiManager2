package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PassengerGroup;
import model.PassengerGroupsList;
import views.PassengersGUI;

public class PassengersController {
    private PassengerGroupsList pgl;
	private PassengersGUI view;

	public PassengersController(PassengerGroupsList pgl, PassengersGUI v) {
		this.pgl = pgl;
		view = v;
		view.addNewPassengersGroupListener(new NewPasssengersGroupController());
	}
	
    class NewPasssengersGroupController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
			pgl.addPassengerGroups(new PassengerGroup("My new destination",5));
        }
    }

}
