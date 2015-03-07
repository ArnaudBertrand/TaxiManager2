package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Manager;
import views.ManagerGUI;

public class ManagerController {
    private Manager manager;
	private ManagerGUI view;

	public ManagerController(Manager m, ManagerGUI v) {
		manager = m;
		view = v;
		view.addStartServiceListener(new TaxiServiceController());
	}
	
    class TaxiServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.disableProcessButton();
    		Thread thread = new Thread (manager);
    		thread.start();
        }
    }

}
