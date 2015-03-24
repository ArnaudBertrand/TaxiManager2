package model;

import java.util.ArrayList;
/**
 * Class for window list model
 */
public class WindowList{
	/** window list **/
	private ArrayList<Window> windowList;

	/**
	 * Constructor
	 * @param window list
	 * */
	public WindowList(){
		windowList = new ArrayList<Window>();
	}
	
	/**
	 * Add window
	 * @param window
	 * */
	public void add(Window k) {
		windowList.add(k);
	}
	
	/**
	 * Find window
	 * @param id window id to look for
	 * @return window matched or null
	 * */
	public Window find(int id) {
		Window window = null;
		for (Window w : windowList) {
			if (w.getWindowID()== id){
				window = w;
				break;
			}
		}
		return window;
	}
	
	/**
	 * Get the window
	 * @param i index to look
	 * @return correct window
	 * */
	public Window get(int i) {
		return windowList.get(i);
	}
	
	/**
	 * Get the size of the window list
	 * @return size of the window list
	 * */
	public int getSize() {
		return windowList.size();
	}
	
}
