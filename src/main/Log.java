package main;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private static Log instance = new Log();
	private static StringBuilder logs;
	private Log(){
		logs = new StringBuilder();
	};
	
	public static Log getInstance(){
		return instance;
	}
	
	public void log(String text){
		System.out.println(text);
		logs.append(text + "\n");
	}
	
	public void export(String filename){
		FileWriter fw;
		try {
		    fw = new FileWriter(filename);
		    fw.write("##### Report #####\n");
		    fw.write(logs.toString());
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}
}
