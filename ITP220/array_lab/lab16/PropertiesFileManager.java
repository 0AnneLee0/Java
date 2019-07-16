package lab16;
//creates/writes properties into a file and reads properties into memory

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileManager {
	//---constant for Properties file name---
	private final static String FILENAME_1 = "props.dat";

	//---writing properties to file--- 
	public static void saveProperties(Properties props) {
		try {
			FileOutputStream output = new FileOutputStream(FILENAME_1);
			//props.store() gives a header within the file on the first line 
			props.store(output,"Properties");
			output.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//---read properties file and load the properties into memory---
	public static void loadProperties(Properties props) {
		try {
			FileInputStream input = new FileInputStream(FILENAME_1);
			props.load(input);
			input.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
