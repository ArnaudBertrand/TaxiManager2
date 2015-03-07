package main;

import model.Manager;
import views.ManagerGUI;
import controller.ManagerController;


public class Main {
	
	public static void main(String[] args) {
    	Manager model = new Manager();
        ManagerGUI view  = new ManagerGUI (model);
        ManagerController controller = new ManagerController(model, view);   
        view.setVisible(true);

	}
}
