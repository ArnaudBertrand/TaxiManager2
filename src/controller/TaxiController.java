package controller;

import generator.TaxiGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        	taxiList.addTaxi(TaxiGenerator.getInstance().generate());
        }
    }
}
