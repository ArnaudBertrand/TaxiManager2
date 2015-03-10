package main;

import model.Manager;
import views.ManagerGUI;
import views.PassengersGUI;
import views.TaxiGUI;
import controller.ManagerController;
import controller.PassengersController;
import controller.TaxiController;


public class Main {
	
	public static void main(String[] args) {
		// Models
    	Manager manModel = new Manager();
    	
    	// Views
    	TaxiGUI taxiView = new TaxiGUI(manModel);
    	PassengersGUI pgView = new PassengersGUI(manModel);
        ManagerGUI manView = new ManagerGUI (manModel,taxiView,pgView);
        
        // Controllers
        new ManagerController(manModel, manView);
        new PassengersController(manModel.getPassengerGroupList(),pgView);
        new TaxiController(manModel.getTaxiList(),taxiView);
        
        manView.setVisible(true);

	}
}
