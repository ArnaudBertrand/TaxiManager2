package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Manager;

public class PassengersGUI extends JPanel implements Observer {

	/** Serial **/
	private static final long serialVersionUID = -3183479849655292033L;
	
	/** Gui variables **/
	private JTextArea jtaPassengers;
	private Manager manager;
	
	public PassengersGUI(Manager m) {
		jtaPassengers = new JTextArea(10, 30);
		manager = m;
		m.addObserver(this);
		
		jtaPassengers.setText(manager.getPassengerGroupList().toString());
		this.add(jtaPassengers);
	}

	public void update(Observable o, Object arg) {
		jtaPassengers.setText(manager.getPassengerGroupList().toString());
	}
}
