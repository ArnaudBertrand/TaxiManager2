package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Log;
import model.Manager;
import views.ManagerGUI;

public class ManagerController {
    private Manager manager;
	private ManagerGUI view;

	public ManagerController(Manager m, ManagerGUI v) {
		manager = m;
		view = v;
		view.addStartServiceListener(new ManagerStartServiceController());
		view.addStopServiceListener(new ManagerStopServiceController());
		view.addIncreaseSpeedListener(new IncreaseSpeedController());
		view.addDecreaseSpeedListener(new DecreaseSpeedController());
	}
	
    class ManagerStartServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.disableStartButton();
        	view.enableStopButton();
    		Thread thread = new Thread (manager);
    		thread.start();
    		Log.getInstance().log("Starting");
        }
    }    
    
    class ManagerStopServiceController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	manager.setFinished(true);
        }
    }

    class IncreaseSpeedController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.setSpeed(manager.increaseSpeed());
        }
    }
    
    class DecreaseSpeedController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.setSpeed(manager.decreaseSpeed());
        }
    }
}
