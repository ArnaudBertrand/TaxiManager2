package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.WindowList;
import model.Manager;

public class ManagerGUI extends JFrame {
	/** Serial */
	private static final long serialVersionUID = -6916776045066988505L;
	/** Variables **/
	private Manager manager;
	private WindowList kiosks;
    private JButton processButton;
	
	public ManagerGUI(Manager manager) {
		this.manager = manager;
		kiosks = manager.getKioskList();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        setLocation(10,20);
		setTitle("Manager");
		
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createKioskPanel(), BorderLayout.CENTER);
        contentPane.add(createTaxisAndPassengersPanel(), BorderLayout.SOUTH);
        
        //pack and set visible
        pack();
        setVisible(true);
	}	

	private JPanel createNorthPanel() {
        //north panel shows the button to start processing
        processButton = new JButton("Start service");
        JPanel northPanel = new JPanel();
        northPanel.add(processButton);
        return northPanel;
    }
    
    private JPanel createKioskPanel() {
    	JPanel kPanel = new JPanel(new GridLayout (1,kiosks.getSize()));
    	for(int i = 0; i < kiosks.getSize(); i++){
    		kPanel.add(new WindowGUI(kiosks.get(i)));
    	}
		return kPanel;
    }
    
    private Component createTaxisAndPassengersPanel() {
    	JPanel tpPanel = new JPanel(new GridLayout (1,2));
    	tpPanel.add(new PassengersGUI(manager));
    	tpPanel.add(new TaxiGUI(manager));
		return tpPanel;
	}
    
    public void addStartServiceListener(ActionListener al) {
        processButton.addActionListener(al);
    }
    
    public void disableProcessButton() {
    	processButton.setEnabled(false);
    }
}
