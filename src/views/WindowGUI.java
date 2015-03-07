package views;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Journey;
import model.Window;
import model.PassengerGroup;
import model.Taxi;

public class WindowGUI extends JPanel implements Observer {
	/** Serial */
	private static final long serialVersionUID = 4276444901405177177L;
	/** Variables **/
	private JTextField textKioskID;
	private JTextField textKioskIDValue;
	private JTextField textDestination;
	private JTextField textDestinationValue;
	private JTextField textPassengers;
	private JTextField textPassengersValue;
	private JTextField textTaxi;
	private JTextField textTaxiValue;
	private Window k;
	private Journey curJourney;
	
	public WindowGUI(Window k){
		this.k = k;
		k.addObserver(this);
		setLayout(new GridLayout(4,2));
		initWindow();
	}
	
	private void initWindow(){
		// Add the taxis list
		textKioskID = new JTextField("Window");
		this.add(textKioskID);
		textKioskIDValue = new JTextField(String.valueOf(k.getKioskID()));
		this.add(textKioskIDValue);
		textDestination = new JTextField("Destination");
		this.add(textDestination);
		textDestinationValue = new JTextField();
		this.add(textDestinationValue);
		textPassengers = new JTextField("Passengers");
		this.add(textPassengers);
		textPassengersValue = new JTextField();
		this.add(textPassengersValue);
		textTaxi = new JTextField("Taxi");
		this.add(textTaxi);
		textTaxiValue = new JTextField();
		this.add(textTaxiValue);
		textKioskID.setIgnoreRepaint(true);
		textKioskIDValue.setIgnoreRepaint(true);
		textDestination.setIgnoreRepaint(true);
		textDestinationValue.setIgnoreRepaint(true);
	}
	
	public void update(Observable o, Object arg) {
		curJourney = k.getCurrentJourney();
		Taxi t = curJourney.getTaxi();
		PassengerGroup g = curJourney.getPassengerGroup();
		
		if(t != null && g != null){
			textDestinationValue.setText(g.getDestination());
			textPassengersValue.setText(String.valueOf(g.getNbPeople()));
			textTaxiValue.setText(t.getRegNb());			
		} else {
			textDestinationValue.setText("");
			textPassengers.setText("");
			textTaxiValue.setText("");
		}
	}	
}
