package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Window;
import views.WindowGUI;

public class WindowController {
    private Window window;
	private WindowGUI view;

	public WindowController(Window w, WindowGUI v) {
		window = w;
		view = v;
		view.addResumeServiceListener(new ManagerResumeServiceController());
		view.addPauseServiceListener(new ManagerPauseServiceController());
	}
	
    class ManagerResumeServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.enablePauseButton();
        	view.disableResumeButton();
        	window.setPaused(false);
        }
    }
    
    class ManagerPauseServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.enableResumeButton();
        	view.disablePauseButton();
        	window.setPaused(true);
        }
    }
}
