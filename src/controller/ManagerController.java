package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Log;
import model.Manager;
import views.ManagerGUI;
/**
 * Controller class for Manager
 */
public class ManagerController {
	/** Manager model **/
    private Manager manager;
    /** Manager view **/
	private ManagerGUI view;

	/**
	 * Constructor for manager controller
	 * @param m model of manager
	 * @param v view of manager
	 */
	public ManagerController(Manager m, ManagerGUI v) {
		manager = m;
		view = v;
		// Set the actions listeners
		view.addStartServiceListener(new ManagerStartServiceController());
		view.addStopServiceListener(new ManagerStopServiceController());
		view.addIncreaseSpeedListener(new IncreaseSpeedController());
		view.addDecreaseSpeedListener(new DecreaseSpeedController());
	}
	
	/**
	 * Action listener to start the service
	 */
    class ManagerStartServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Disable Start and Enable Stop
        	view.disableStartButton();
        	view.enableStopButton();
        	
        	// Launch the thread
    		Thread thread = new Thread (manager);
    		thread.start();
    		Log.getInstance().log("Starting");
        }
    }    
    
    /**
     * Action listener to stop the service
     */
    class ManagerStopServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Finish the program
        	manager.setFinished(true);
        }
    }

    /**
     * Action listener to increase the speed of the simulation
     */
    class IncreaseSpeedController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Increase speed
        	view.setSpeed(manager.increaseSpeed());
        }
    }
    
    /**
     * Action listener to decrease the speed of the simulation
     */
    class DecreaseSpeedController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Decrease speed
        	view.setSpeed(manager.decreaseSpeed());
        }
    }
}
