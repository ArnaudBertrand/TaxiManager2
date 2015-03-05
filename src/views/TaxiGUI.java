package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.PassengerGroup;
import model.PassengerGroupsList;
import model.Taxi;
import model.TaxiList;

public class TaxiGUI extends JFrame {

	// GUI components
	private JTextField nbTaxis = new JTextField();
	private JButton updateButton = new JButton("Update");
	private JTextArea jtaPassengers = new JTextArea(10, 30);
	private TaxiList model;
	private int NbOfTaxis = 0;

	public TaxiGUI(TaxiList model) {
		this.model = model;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// add start panel at the top
		add(BorderLayout.NORTH, createSetTaxisPanel());
		// Add the passenger groups
		add(BorderLayout.CENTER, jtaPassengers);

		setSize(700, 300);
		setVisible(true);
		setTitle("Taxis List");
	}

	// Method to add the passenger groups
	private void createList(TaxiList model) {
		jtaPassengers.setText("");
		// Go through the list of passenger groups
		Iterator<Taxi> taxi = model.getTaxiList().iterator();
		int counter = 0;
		// Add the passenger groups until reaching the number wanted
		while (taxi.hasNext() && counter < NbOfTaxis) {
			// Add the passenger group to the TextArea
			jtaPassengers.append(taxi.next().toString() + "\n \n");
			counter++;
		}
	}

	public JPanel createSetTaxisPanel() {
		JPanel setTaxisPanel = new JPanel(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.add(new JLabel("Number of taxis : "));
		panel.add(nbTaxis);
		panel.add(updateButton);
		setTaxisPanel.add(BorderLayout.CENTER, panel);

		JPanel passengerGroups = new JPanel();
		setTaxisPanel.add(BorderLayout.SOUTH, passengerGroups);

		return setTaxisPanel;
	}

	// return contents of nbPassengerGroups text box
	public String getNbOfSeats() {
		return nbTaxis.getText();
	}

	// add listener to update button
	public void addSetListener(ActionListener al) {
		updateButton.addActionListener(al);
	}

	// Set the number of Passenger groups wanted
	public void setNbTaxis(int i) {
		NbOfTaxis = i;
		createList(model);
	}


}
