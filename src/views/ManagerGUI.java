package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Manager;
import model.Window;
import model.WindowList;
import controller.WindowController;

/**
 * Class for manager view
 */
public class ManagerGUI extends JFrame implements Observer{
	/** Serial */
	private static final long serialVersionUID = -6916776045066988505L;
	/** Variables **/
	private WindowList windows;
	private Manager m;
	
	/** GUI Elements **/
    private JButton startButton;
    private JButton stopButton;
	private JLabel speedControlText;
    private JButton speedUpButton;
    private JButton speedDownButton;
	private JLabel speedControlValue;

	/**
	 * Constructor
	 * @param manager manager model
	 * @param tg taxi view
	 * @param pgView passenger view
	 */
	public ManagerGUI(Manager manager, TaxiGUI tg, PassengersGUI pgView) {
		// Initiate variables
		m = manager;
		windows = manager.getWindowsList();
		manager.addObserver(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Initiate view
        setLocation(10,20);
		setTitle("Manager");		
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createWindowsPanel(), BorderLayout.CENTER);
        contentPane.add(createTaxisAndPassengersPanel(tg,pgView), BorderLayout.SOUTH);
        
        // Pack and set visible
        pack();
        setVisible(true);
	}	

	/**
	 * Create North panel
	 * @return nort panel
	 */
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
        speedControlValue = new JLabel("1");
        speedUpButton = new JButton("\u2191");
        northPanel.add(speedControlText);
        northPanel.add(speedDownButton);
        northPanel.add(speedControlValue);
        northPanel.add(speedUpButton);
        
        return northPanel;
    }
    
	/**
	 * Create windows panel
	 * @return windows panel
	 */
    private JPanel createWindowsPanel() {
    	JPanel wPanel = new JPanel(new GridLayout (1,windows.getSize(),5,52));
    	// Add new view for each window and their controller
    	for(int i = 0; i < windows.getSize(); i++){
    		Window window = windows.get(i);
    		WindowGUI wGui = new WindowGUI(window);
    		new WindowController(window,wGui);
    		wPanel.add(wGui);
    	}
		return wPanel;
    }
    
    /**
     * Create Taxi and Passengers panel
     * @param tg Taxi view
     * @param pgView Passenger view
     * @return Taxi and Passengers panel
     */
    private Component createTaxisAndPassengersPanel(TaxiGUI tg, PassengersGUI pgView) {
    	JPanel tpPanel = new JPanel(new GridLayout (1,2));
    	tpPanel.add(pgView);
    	tpPanel.add(tg);
		return tpPanel;
	}
    
    /**
     * Add start simulation listener
     * @param al action listener
     */
    public void addStartServiceListener(ActionListener al) {
        startButton.addActionListener(al);
    }
    
    /**
     * Add stop simulation listener
     * @param al action listener
     */
    public void addStopServiceListener(ActionListener al) {
        stopButton.addActionListener(al);
    }
    
    /**
     * Add increase speed listener
     * @param al action listener
     */
	public void addIncreaseSpeedListener(ActionListener al) {
		speedUpButton.addActionListener(al);
	}
	
	/**
	 * Add decrease speed listener
     * @param al action listener
	 */
	public void addDecreaseSpeedListener(ActionListener al) {
		speedDownButton.addActionListener(al);
	}

	/**
	 * Disable the start button
	 */
    public void disableStartButton() {
    	startButton.setEnabled(false);
    }
    
    /**
     * Enable the stop button
     */
	public void enableStopButton() {
		stopButton.setEnabled(true);
	}
	
	/**
	 * Set the speed value
	 * @param speed value of current speed
	 */
	public void setSpeed(int speed){
		speedControlValue.setText(String.valueOf(speed));
	}
	
	/**
	 * Update function
	 */
	public void update(Observable o, Object arg) {
		// Print message when simulation ends
		if(m.isFinished()){
			JOptionPane.showMessageDialog(this, "Simulation has ended. Thanks for playing with us ;).");			
		}
	}
	
}
