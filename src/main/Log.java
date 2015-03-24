package main;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for logs
 */
public class Log {
	/** Log instance **/
	private static Log instance = new Log();
	/** Logs **/
	private static StringBuilder logs;
	
	/**
	 * Constructor
	 */
	private Log(){
		logs = new StringBuilder();
	};
	
	/**
	 * Get log instance
	 * @return log instance
	 */
	public static Log getInstance(){
		return instance;
	}
	
	/**
	 * Set a new text in the log
	 * @param text text to insert in log
	 */
	public void log(String text){
		System.out.println(text);
		logs.append(text + "\n");
	}
	
	/**
	 * Exports logs in a file
	 * @param filename name of the file to export logs
	 */
	public void export(String filename){
		FileWriter fw;
		try {
		    fw = new FileWriter(filename);
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
