package model;

import java.util.ArrayList;

public class WindowList{
	// Init variables
	private ArrayList<Window> kioskList;

	public WindowList(){
		kioskList = new ArrayList<Window>();
	}
	
	public void add(Window k) {
		kioskList.add(k);
	}
	
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
	
	public Window get(int i) {
		return kioskList.get(i);
	}
	
	
	public int getSize() {
		return kioskList.size();
	}
	
}
