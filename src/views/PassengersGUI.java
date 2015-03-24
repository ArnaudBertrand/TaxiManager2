package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Manager;
import model.PassengerGroupsList;

/**
 * Class for passenger view
 */
public class PassengersGUI extends JPanel implements Observer {

	/** Serial **/
	private static final long serialVersionUID = -3183479849655292033L;
	
	/** Variables **/
	private Manager manager;
	
	/** Gui variables **/
	private JScrollPane jsp;
	private JTextArea jtaPassengers;
	private JButton addPassengersBtn;
	
	/**
	 * Constructor
	 * @param m manager model
	 */
	public PassengersGUI(Manager m) {
		// Initiate variable
		manager = m;
		// Observe manager model
		m.addObserver(this);
		
		// Init view
		jtaPassengers = new JTextArea(10, 50);
		jsp = new JScrollPane(jtaPassengers);
		jtaPassengers.setEditable(false);
		this.setLayout(new BorderLayout());
		initNorthLayout();
		initCenterLayout();
	}
	
	/**
	 * Init north layout
	 */
	private void initNorthLayout() {
		addPassengersBtn = new JButton("Add new Passengers");
		this.add(addPassengersBtn, BorderLayout.NORTH);
	}
	
	/**
	 * Init center layout
	 */
	private void initCenterLayout(){
		PassengerGroupsList pgl = manager.getPassengerGroupList();
		String text = "PASSENGER GROUPS (" + pgl.getSize() + ") \n\n";
		text+= pgl.toString();
		jtaPassengers.setText(text);
		this.add(jsp,BorderLayout.CENTER);		
	}

	/**
	 * Update observer function
	 */
	public void update(Observable o, Object arg) {
		// Update the passenger group list
		PassengerGroupsList pgl = manager.getPassengerGroupList();
		String text = "PASSENGER GROUPS(" + pgl.getSize() + ") \n\n";
		text+= pgl.toString();
		jtaPassengers.setText(text);
	}
	
	/**
	 * Add new passenger group listener
	 * @param al action listener
	 */
	public void addNewPassengersGroupListener(ActionListener al) {
		addPassengersBtn.addActionListener(al);
	}
}
