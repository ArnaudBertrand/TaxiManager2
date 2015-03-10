package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Manager;
import model.Window;
import model.WindowList;
import controller.WindowController;

public class ManagerGUI extends JFrame {
	/** Serial */
	private static final long serialVersionUID = -6916776045066988505L;
	/** Variables **/
	private WindowList windows;
    private JButton startButton;
    private JButton stopButton;
    
	private JLabel speedControlText;
    private JButton speedUpButton;
    private JButton speedDownButton;
	private JLabel speedControlValue;

	public ManagerGUI(Manager manager, TaxiGUI tg, PassengersGUI pgView) {
		windows = manager.getWindowsList();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        setLocation(10,20);
		setTitle("Manager");
		
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createKioskPanel(), BorderLayout.CENTER);
        contentPane.add(createTaxisAndPassengersPanel(tg,pgView), BorderLayout.SOUTH);
        
        //pack and set visible
        pack();
        setVisible(true);
	}	

	private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel();
        // Start / Stop
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        northPanel.add(startButton);
        northPanel.add(stopButton);
        
        // Speed control
        speedControlText = new JLabel("Speed control");
        speedDownButton = new JButton("\u2193");
        speedDownButton.setOpaque(true);
        speedControlValue = new JLabel("1");
        speedUpButton = new JButton("\u2191");
        northPanel.add(speedControlText);
        northPanel.add(speedDownButton);
        northPanel.add(speedControlValue);
        northPanel.add(speedUpButton);
        
        return northPanel;
    }
    
    private JPanel createKioskPanel() {
    	JPanel kPanel = new JPanel(new GridLayout (1,windows.getSize(),5,52));
    	for(int i = 0; i < windows.getSize(); i++){
    		Window window = windows.get(i);
    		WindowGUI wGui = new WindowGUI(window);
    		new WindowController(window,wGui);
    		kPanel.add(wGui);
    	}
		return kPanel;
    }
    
    private Component createTaxisAndPassengersPanel(TaxiGUI tg, PassengersGUI pgView) {
    	JPanel tpPanel = new JPanel(new GridLayout (1,2));
    	tpPanel.add(pgView);
    	tpPanel.add(tg);
		return tpPanel;
	}
    
    public void addStartServiceListener(ActionListener al) {
        startButton.addActionListener(al);
    }
    
    public void addStopServiceListener(ActionListener al) {
        stopButton.addActionListener(al);
    }
    
	public void addIncreaseSpeedListener(ActionListener al) {
		speedUpButton.addActionListener(al);
	}
	
	public void addDecreaseSpeedListener(ActionListener al) {
		speedDownButton.addActionListener(al);
	}
    
    public void disableStartButton() {
    	startButton.setEnabled(false);
    }
	
	public void enableStopButton() {
		stopButton.setEnabled(true);
	}
	
	public void setSpeed(int speed){
		speedControlValue.setText(String.valueOf(speed));
	}
	
}
