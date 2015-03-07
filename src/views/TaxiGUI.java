package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Manager;

public class TaxiGUI extends JPanel implements Observer {

	/** Serial **/
	private static final long serialVersionUID = 7514483252824776637L;
	
	/** Variables **/
	private JTextArea jtaTaxis;
	private Manager manager;
	
	public TaxiGUI(Manager m) {
		jtaTaxis  = new JTextArea(10, 30);
		jtaTaxis.setEditable(false);
		manager = m;
		m.addObserver(this);
		
		jtaTaxis.setText(manager.getTaxiList().getTaxiListByRegNb());
		this.add(jtaTaxis);
	}
	
	public void update(Observable o, Object arg) {
		jtaTaxis.setText(manager.getTaxiList().getTaxiListByRegNb());
	}
}
