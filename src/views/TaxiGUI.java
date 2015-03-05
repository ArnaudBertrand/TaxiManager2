package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Taxi;
import model.TaxiList;

public class TaxiGUI extends JFrame {

	/** GUI components **/
	private JTextField nbTaxis = new JTextField();
	private JButton updateButton = new JButton("Update");
	private JTextArea jtaTaxis = new JTextArea(10, 30);
	private JScrollPane scrollPane = new JScrollPane(jtaTaxis,
				                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private TaxiList model;
	private int NbOfTaxis = 0;

	/**
	 * Constructor
	 * @param model the taxi list
	 */
	public TaxiGUI(TaxiList model) {
		this.model = model;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// add start panel at the top
		add(BorderLayout.NORTH, createSetTaxisPanel());
		// Add the taxis list
		add(BorderLayout.CENTER, jtaTaxis);
		//add(BorderLayout.CENTER, scrollPane);---------------marche pas !!
		
		setSize(700, 300);
		setVisible(true);
		setTitle("Taxis List");
	}

	/**
	 * Method to create the taxi list to display reaching the number wanted
	 * @param model the taxi list
	 */
	private void createList(TaxiList model) {
		jtaTaxis.setText("");
		// Go through the list of taxis
		Iterator<Taxi> taxi = model.getTaxiList().iterator();
		int counter = 0;
		// Add the number of taxis from the list until reaching the number wanted
		while (taxi.hasNext() && counter < NbOfTaxis) {
			// Add the taxi to the TextArea
			jtaTaxis.append(taxi.next().ToString() + "\n");
			counter++;
		}
	}

	/**
	 * Method to create the Taxis Panel
	 * @return the taxis panel
	 */
	public JPanel createSetTaxisPanel() {
		JPanel setTaxisPanel = new JPanel(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.add(new JLabel("Number of taxis : (1-26) "));
		panel.add(nbTaxis);
		panel.add(updateButton);
		setTaxisPanel.add(BorderLayout.CENTER, panel);

		JPanel taxisList = new JPanel();
		setTaxisPanel.add(BorderLayout.SOUTH, taxisList);

		return setTaxisPanel;
	}

	/**
	 * Get the number of taxis in the text box
	 * @return number of taxis
	 */
	public String getNbOfTaxis() {
		return nbTaxis.getText();
	}

	/**
	 * Method to add listener to the update button
	 * @param al the action listener
	 */
	public void addSetListener(ActionListener al) {
		updateButton.addActionListener(al);
	}

	/**
	 * Set the number of taxis wanted
	 * @param i number of taxis wanted
	 */
	public void setNbOfTaxis(int i) {
		NbOfTaxis = i;
		createList(model);
	}


}
