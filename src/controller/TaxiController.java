package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exceptions.RegNbFormatException;
import model.Taxi;
import model.TaxiList;
import views.TaxiGUI;

public class TaxiController {
    private TaxiList taxiList;
	private TaxiGUI view;

	public TaxiController(TaxiList tl, TaxiGUI v) {
		taxiList = tl;
		view = v;
		view.addNewTaxiListener(new NewTaxiController());
	}
	
    class NewTaxiController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        	try {
				taxiList.addTaxi(new Taxi("NEW-TAXI00",5));
			} catch (RegNbFormatException e1) {
				System.out.println("Error adding a new taxi: " + e1.getMessage());
			}
        }
    }
}
