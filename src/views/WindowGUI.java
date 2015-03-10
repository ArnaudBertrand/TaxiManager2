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
	private Window k;
	private Journey curJourney;
	private JTextArea jta;
	private JButton pauseButton;
	private JButton resumeButton;
	
	public WindowGUI(Window k){
		this.k = k;
		k.addObserver(this);
		
		initNorth();
		initCenter();        
	}
	
	private void initNorth(){
		this.setLayout(new BorderLayout());
		
		JPanel pan = new JPanel();
		pauseButton = new JButton("Pause");
        pan.add(pauseButton);
        resumeButton = new JButton("Resume");
        resumeButton.setEnabled(false);
        pan.add(resumeButton);
		
        this.add(pan, BorderLayout.NORTH);
	}
	
	private void initCenter(){
		jta = new JTextArea(4,35);
		jta.setFont(new Font("Monospaced", Font.PLAIN, 12));
		jta.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		this.add(jta,BorderLayout.CENTER);
	}
	
	public void update(Observable o, Object arg) {
		curJourney = k.getCurrentJourney();
		Taxi t = curJourney.getTaxi();
		PassengerGroup g = curJourney.getPassengerGroup();
		StringBuilder sb = new StringBuilder();
		sb.append("WINDOW " + String.valueOf(k.getKioskID()) + "\n");
		if(t != null && g != null){
			sb.append(String.format("%-20s%-20s", "Destination: ", g.getDestination().trim()) + "\n");
			sb.append(String.format("%-20s%-20s", "Passengers: ", String.valueOf(g.getNbPeople()).trim()) + "\n");
			sb.append(String.format("%-20s%-20s", "Taxi: ", t.getRegNb().trim()));
		}
		jta.setText(sb.toString());
	}
	
    public void addPauseServiceListener(ActionListener al) {
        pauseButton.addActionListener(al);
    }

    public void addResumeServiceListener(ActionListener al) {
        resumeButton.addActionListener(al);
    }
    
	public void enablePauseButton() {
		pauseButton.setEnabled(true);
	}
	
	public void disablePauseButton() {
		pauseButton.setEnabled(false);
	}
	
	public void enableResumeButton() {
		resumeButton.setEnabled(true);
	}

	public void disableResumeButton() {
		resumeButton.setEnabled(false);
	}
}