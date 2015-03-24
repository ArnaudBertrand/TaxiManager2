package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Journey;
import model.PassengerGroup;
import model.Taxi;
import model.Window;

public class WindowGUI extends JPanel implements Observer {
	/** Serial */
	private static final long serialVersionUID = 4276444901405177177L;
	
	/** Variables **/
	private Window w;
	private Journey curJourney;
	
	/** Gui variables **/
	private JTextArea jta;
	private JButton pauseButton;
	private JButton resumeButton;
	
	/**
	 * Constructor
	 * @param w window
	 */
	public WindowGUI(Window w){
		// Init variables
		this.w = w;
		// Subscribe to window
		w.addObserver(this);
		
		// Init view
		initNorth();
		initCenter();        
	}
	
	/**
	 * Init north panel
	 */
	private void initNorth(){
		this.setLayout(new BorderLayout());
		
		// Add pause and resume buttons
		JPanel pan = new JPanel();
		pauseButton = new JButton("Pause");
        pan.add(pauseButton);
        resumeButton = new JButton("Resume");
        resumeButton.setEnabled(false);
        pan.add(resumeButton);
		
        this.add(pan, BorderLayout.NORTH);
	}
	
	/**
	 * Init center panel
	 */
	private void initCenter(){
		// Add text area
		jta = new JTextArea(4,35);
		jta.setFont(new Font("Monospaced", Font.PLAIN, 12));
		jta.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		this.add(jta,BorderLayout.CENTER);
	}
	
	/**
	 * Update function for observer pattern
	 */
	public void update(Observable o, Object arg) {
		// Get the journey
		curJourney = w.getCurrentJourney();
		StringBuilder sb = new StringBuilder("WINDOW " + String.valueOf(w.getWindowID()) + "\n");
		if(curJourney != null){
			// Get the taxi and passenger
			Taxi t = curJourney.getTaxi();
			PassengerGroup g = curJourney.getPassengerGroup();
			// Update the view
			if(t != null && g != null){
				sb.append(String.format("%-20s%-20s", "Destination: ", g.getDestination().trim()) + "\n");
				sb.append(String.format("%-20s%-20s", "Passengers: ", String.valueOf(g.getNbPeople()).trim()) + "\n");
				sb.append(String.format("%-20s%-20s", "Taxi: ", t.getRegNb().trim()));
			} else {
				sb.append("Journey not valid");				
			}
		} else {
			sb.append("Paused or stopped");
		}
		jta.setText(sb.toString());
	}
	
	/**
	 * Add pause listener
	 * @param al action listener
	 */
    public void addPauseServiceListener(ActionListener al) {
        pauseButton.addActionListener(al);
    }
    
    /**
     * Add resume listener
	 * @param al action listener
     */
    public void addResumeServiceListener(ActionListener al) {
        resumeButton.addActionListener(al);
    }

    /**
     * Enable pause button
     */
	public void enablePauseButton() {
		pauseButton.setEnabled(true);
	}
	
	/**
	 * Disable pause button
	 */
	public void disablePauseButton() {
		pauseButton.setEnabled(false);
	}
	
	/**
	 * Enable resume button
	 */
	public void enableResumeButton() {
		resumeButton.setEnabled(true);
	}

	/**
	 * Disable resume button
	 */
	public void disableResumeButton() {
		resumeButton.setEnabled(false);
	}
}