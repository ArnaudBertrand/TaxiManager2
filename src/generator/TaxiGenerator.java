package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Taxi;
import exceptions.RegNbFormatException;

public class TaxiGenerator {
	private static TaxiGenerator instance = new TaxiGenerator();
	private List<String> cityList;
	
	private TaxiGenerator(){
		cityList = new ArrayList<String>();
		initCities();
	}
	
	public static TaxiGenerator getInstance(){
		return instance;
	}
	
	public synchronized Taxi generate(){
		String reg = pickRandomCity() + "-" + pickRandomReg();
		Taxi t = null;
		while(t == null){
			try {
				t = new Taxi(reg,pickRandomNbSeat());
			} catch (RegNbFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		return t;
	}
	
	private void initCities(){
		cityList.add("LON");
		cityList.add("OXF");
		cityList.add("MAN");
		cityList.add("LIV");
	}
		
	private String pickRandomCity(){
		Collections.shuffle(cityList);
		return cityList.get(0);
	}
	
	private String pickRandomReg(){
		String reg = "";
		Random r = new Random();
		for(int i = 0; i<6; i++){
			int j = r.nextInt(26);
			if(j<10){
				reg += (char)(j + '0');
			} else {
				reg += (char)(j - 10 + 'A');
			}
		}
		return reg;
	}
	
	private int pickRandomNbSeat() {
	    Random rand = new Random();
	    // Random number of seats between 4 and 6
	    int randomNum = rand.nextInt(3) + 4;
	    return randomNum;
	}
}