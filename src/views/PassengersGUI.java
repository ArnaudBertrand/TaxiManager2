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

public class PassengersGUI extends JPanel implements Observer {

	/** Serial **/
	private static final long serialVersionUID = -3183479849655292033L;
	
	/** Gui variables **/
	private JScrollPane jsp;
	private JTextArea jtaPassengers;
	private Manager manager;
	private JButton addPassengersBtn;
	
	public PassengersGUI(Manager m) {
		manager = m;
		m.addObserver(this);
		
		jtaPassengers = new JTextArea(10, 50);
		jsp = new JScrollPane(jtaPassengers);
		jtaPassengers.setEditable(false);
		
		this.setLayout(new BorderLayout());
		
		initNorthLayout();
		initCenterLayout();
	}
	
	private void initNorthLayout() {
		addPassengersBtn = new JButton("Add new Passengers");
		this.add(addPassengersBtn, BorderLayout.NORTH);
	}
	
	private void initCenterLayout(){
		PassengerGroupsList pgl = manager.getPassengerGroupList();
		String text = "PASSENGER GROUPS (" + pgl.getSize() + ") \n\n";
		text+= pgl.toString();
		jtaPassengers.setText(text);
		this.add(jsp,BorderLayout.CENTER);		
	}

	public void update(Observable o, Object arg) {
		PassengerGroupsList pgl = manager.getPassengerGroupList();
		String text = "PASSENGER GROUPS(" + pgl.getSize() + ") \n\n";
		text+= pgl.toString();
		jtaPassengers.setText(text);
	}
	
	public void addNewPassengersGroupListener(ActionListener al) {
		addPassengersBtn.addActionListener(al);
	}
}
