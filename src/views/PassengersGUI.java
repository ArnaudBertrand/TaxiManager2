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

public class PassengersGUI extends JFrame {

	// GUI components
	private JTextField nbPassengerGroups = new JTextField();
	private JButton updateButton = new JButton("Update");
	private JTextArea jtaPassengers = new JTextArea(10, 30);
	private PassengerGroupsList model;
	private int NbPG = 0;

	public PassengersGUI(PassengerGroupsList model) {
		this.model = model;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// add start panel at the top
		add(BorderLayout.NORTH, createSetPassengerGroupsPanel());
		// Add the passenger groups
		add(BorderLayout.CENTER, jtaPassengers);

		setSize(700, 300);
		setVisible(true);
		setTitle("Passenger Groups");
	}

	// Method to add the passenger groups
	private void createList(PassengerGroupsList model) {
		jtaPassengers.setText("");
		// Go through the list of passenger groups
		Iterator<PassengerGroup> pg = model.getPassengerGroupsList().iterator();
		int counter = 0;
		// Add the passenger groups until reaching the number wanted
		while (pg.hasNext() && counter < NbPG) {
			// Add the passenger group to the TextArea
			jtaPassengers.append(pg.next().toString() + "\n \n");
			counter++;
		}
	}

	public JPanel createSetPassengerGroupsPanel() {
		JPanel setPGPanel = new JPanel(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.add(new JLabel("Number of passenger groups (1 - 5) : "));
		panel.add(nbPassengerGroups);
		panel.add(updateButton);
		setPGPanel.add(BorderLayout.CENTER, panel);

		JPanel passengerGroups = new JPanel();
		setPGPanel.add(BorderLayout.SOUTH, passengerGroups);

		return setPGPanel;
	}

	// return contents of nbPassengerGroups text box
	public String getNbPG() {
		return nbPassengerGroups.getText();
	}

	// add listener to update button
	public void addSetListener(ActionListener al) {
		updateButton.addActionListener(al);
	}

	// Set the number of Passenger groups wanted
	public void setNbPG(int i) {
		NbPG = i;
		createList(model);
	}

}
