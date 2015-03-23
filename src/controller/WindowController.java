package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Window;
import views.WindowGUI;

/**
 * Controller class for windows
 */
public class WindowController {
	/** Window model **/
    private Window window;
    /** Window view **/
	private WindowGUI view;

	/**
	 * Constructor
	 * @param w window model
	 * @param v window view
	 */
	public WindowController(Window w, WindowGUI v) {
		window = w;
		view = v;
		// Add action listeners
		view.addResumeServiceListener(new ManagerResumeServiceController());
		view.addPauseServiceListener(new ManagerPauseServiceController());
	}
	
	/**
	 * Action listener for resuming a window
	 */
    class ManagerResumeServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Enable pause button and disable resume
        	view.enablePauseButton();
        	view.disableResumeButton();
        	// Resume the window
        	window.setPaused(false);
        }
    }
    
    /**
     * Action listener to pause a window
     */
    class ManagerPauseServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	// Enable resume button and disable pause
        	view.enableResumeButton();
        	view.disablePauseButton();
        	// Pause the window
        	window.setPaused(true);
        }
    }
}
