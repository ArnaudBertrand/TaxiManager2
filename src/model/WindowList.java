package model;

import java.util.ArrayList;

public class WindowList{
	// Init variables
	private ArrayList<Window> kioskList;

	/**
	 * Constructor
	 * @param kiosk list
	 * */
	public WindowList(){
		kioskList = new ArrayList<Window>();
	}
	
	/**
	 * Add window
	 * @param window
	 * */
	public void add(Window k) {
		kioskList.add(k);
	}
	
	/**
	 * Find window
	 * @param id
	 * @return kiosk
	 * */
	public Window find(int id) {
		Window kiosk = null;
		for (Window k : kioskList) {
			if (k.getKioskID()== id){
				kiosk = k;
				break;
			}
		}
		return kiosk;
	}
	
	/**
	 * Get the window
	 * @param i
	 * @return correct window
	 * */
	public Window get(int i) {
		return kioskList.get(i);
	}
	
	/**
	 * Get the size of the window list
	 * @return size of the window list
	 * */
	public int getSize() {
		return kioskList.size();
	}
	
}
