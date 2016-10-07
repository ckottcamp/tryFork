package clueGame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException() {
		super("Bad config format");
	}
	
	public BadConfigFormatException(String fileName) {
		super("Bad config format on file: " + fileName);
		try {
			PrintWriter logFile = new PrintWriter("Exceptions log.txt");
			logFile.println("Bad config format on file: '" + fileName + "'. Please check the file format and run program again.");
			logFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
