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
import model.TaxiList;

public class TaxiGUI extends JPanel implements Observer {

	/** Serial **/
	private static final long serialVersionUID = 7514483252824776637L;
	
	/** Variables **/
	private JScrollPane jsp;
	private JTextArea jtaTaxis;
	private Manager manager;
	private JButton addTaxiBtn;
	
	public TaxiGUI(Manager m) {
		manager = m;
		m.addObserver(this);
		
		jtaTaxis  = new JTextArea(10, 50);
		jsp = new JScrollPane(jtaTaxis);
		jtaTaxis.setEditable(false);
		
		this.setLayout(new BorderLayout());
		
		initNorthLayout();
		initCenterLayout();
	}
	
	private void initNorthLayout(){
		addTaxiBtn = new JButton("Add new Taxi");
		this.add(addTaxiBtn,BorderLayout.NORTH);
	}
	
	private void initCenterLayout(){
		TaxiList tl = manager.getTaxiList();
		String text = "TAXIS (" + tl.getNbTaxi() + ") \n\n";
		text+= tl.getTaxiListByRegNb();
		jtaTaxis.setText(text);
		this.add(jsp,BorderLayout.CENTER);		
	}
	
	public void update(Observable o, Object arg) {
		TaxiList tl = manager.getTaxiList();
		String text = "TAXIS (" + tl.getNbTaxi() + ") \n\n";
		text+= tl.getTaxiListByRegNb();
		jtaTaxis.setText(text);
	}

	public void addNewTaxiListener(ActionListener al) {
        addTaxiBtn.addActionListener(al);
	}
}
