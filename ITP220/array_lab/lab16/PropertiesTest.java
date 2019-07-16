package lab16;
//uses properties class to set up key/value pairs

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class PropertiesTest {
	public static void main(String[]args) {
		//create Properties object
		Properties prop = new Properties();

		//set properties given keys and values
		//keys are unique
		System.out.println("Created properties.\n");
		prop.setProperty("machine_name", "hec217_123");
		prop.setProperty("environment", "dev");
		prop.setProperty("next_id", "100");

		//call listProperties to print values
		listProperties(prop);

		System.out.println("Changed environment to prod.\n");
		//to replace "environment" value (dev) with prod
		prop.setProperty("environment",  "prod");

		//reprint values
		listProperties(prop);

		System.out.println("Saved properties to file.\n");
		//save properties to write contents to file
		saveProperties(prop);

		System.out.println("Cleared properties.\n");
		//clear contents of properties object
		prop.clear();

		//print values
		listProperties(prop);

		System.out.println("Loaded properties.\n");
		//loads values from file
		loadProperties(prop);

		//print values
		listProperties(prop);

		//loop to look up key and print value, and quit
		Scanner keyIn = new Scanner(System.in);
		String key = null;

		System.out.println("Enter key to look up or Q for quit: ");
		key = keyIn.next();

		while (!key.equalsIgnoreCase("q")) {
			String keyLC = key.toLowerCase();
			Object value = prop.getProperty(keyLC); 
			if (value != null) {
				System.out.printf("The value is: %s%n", value);
			}
			else {
				System.out.println("Key not found");
			}
			System.out.println("Enter key to look up or Q for quit: ");
			key = keyIn.next();
		}
		System.out.println("Program complete.");

		keyIn.close();
	}

	private final static String FILENAME = "props.dat";
	Properties table = new Properties();

	//writing memory in Properties object into the file 
	private static void saveProperties(Properties props) {
		try {
			FileOutputStream output = new FileOutputStream(FILENAME);
			//String titleWithinFIle = "Properties";
			//props.store() gives a header within the file on the first line 
			props.store(output,"Properties");
			output.close();
			//listProperties(props);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//loading the properties into Properties object
	private static void loadProperties(Properties props) {
		try {
			FileInputStream input = new FileInputStream(FILENAME);
			props.load(input);
			input.close();
			//listProperties(props);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//accepts the Properties object as a parameter and lists values
	public static void listProperties(Properties props) {
		//void set<> in java collections, every item in list is unique
		Set<Object>keys = props.keySet();
		System.out.println("The properties object contains:");
		if (keys.isEmpty()) {
			System.out.println("Properties empty\n");
		}
		else {
			//looks up keys and returns values
			for (Object key:keys) {
				System.out.printf("key:%s, value:%s%n", key, props.get(key));
				//can also use props.getProperty((String)key);
			}
			//System.out.println("Properties empty");
			System.out.println();
		}
	}

}

