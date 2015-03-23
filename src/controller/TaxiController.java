package controller;

import generator.TaxiGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.TaxiList;
import views.TaxiGUI;

/**
 * Controller class for Taxi
 */
public class TaxiController {
	/** Taxi model **/
    private TaxiList taxiList;
    /** Taxi view **/
	private TaxiGUI view;

	/**
	 * Constructor
	 * @param tl taxi model
	 * @param v taxi view
	 */
	public TaxiController(TaxiList tl, TaxiGUI v) {
		taxiList = tl;
		view = v;
		// Add action listener
		view.addNewTaxiListener(new NewTaxiController());
	}
	
	/**
	 * Action listener for adding new taxi
	 */
    class NewTaxiController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
        	// Add new taxi
        	taxiList.addTaxi(TaxiGenerator.getInstance().generate());
        }
    }
}
