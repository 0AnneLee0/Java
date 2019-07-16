package lab16;
//prompts for student information entry into an array and writes array to file

//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class StudentManager {
	//---create static Properties object---
	private static Properties props = new Properties();
	Properties table = new Properties();

	public static void main(String[]args) {		
		Scanner keyIn = new Scanner(System.in);
		int id = 0;
		String firstName;
		String lastName;
		String option = new String ("y");
		String filename = new String ("student_info.dat");

		System.out.println("Program start.\n");

		//---loads values from file---
		//because properties is method is static "PropertiesFileManager."
		PropertiesFileManager.loadProperties(props);
		System.out.printf("Successfully read from file: {environment=%s, next_id=%s}%n%n", 
				props.getProperty("environment"), props.getProperty("next_id"));

		//convert string to int
		id = Integer.parseInt(props.getProperty("next_id"));

		//---create empty array list object to be filled with NovaStudent objects---
		List<NovaStudent> studentList = new ArrayList<NovaStudent>();

		//---entering student information into array---
		while (option.equalsIgnoreCase("y")) {			
			System.out.print("Enter student first name: ");
			firstName=keyIn.next();
			System.out.print("Enter student last name: ");
			lastName=keyIn.next();

			//add NovaStudent objects to list
			studentList.add(new NovaStudent(id, firstName, lastName));
			System.out.printf("Created record: id:%d  first name:%s  last name:%s%n%n", id, firstName, lastName);
			System.out.print("Do you want to enter another student (Y/N)?  ");
			option=keyIn.next();
			id++;			
		}

		printList(studentList,"");		
		writeRecords(filename, studentList);

		//to replace "next_id" value with incremented number
		//convert to string
		props.setProperty("next_id", Integer.toString(id));

		System.out.printf("%nSuccessfully wrote to file: {environment=%s, next_id=%s}%n%n", props.getProperty("environment"), props.getProperty("next_id"));
		PropertiesFileManager.saveProperties(props);

		//readFromAFile(filename);

		System.out.println("Program end.");

		keyIn.close();
	}


	//prints for any type of object in an array list
	private static void printList(List<? extends Object> list, String header) {
		System.out.printf("%s",header);
		for (Object item: list) {
			System.out.printf("Wrote record: %s", item);
		}
	}
	
	//"persist" the ArrayList to a file containing records
	public static void writeRecords(String filename, List<NovaStudent> listOfObjects) {
		try {
			//create and write to a file
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file, true);
			PrintWriter printwriter = new PrintWriter(filewriter);
			for(NovaStudent item : listOfObjects) {
				printwriter.println(item);
			}
			printwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	//reading file
	public static void readFromAFile(String filename){
		File file = new File(filename);
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader linereader = new BufferedReader(filereader);
			String line = null;
			while (true) {
				if (line == null) {
					break;
				} else {
					linereader.readLine();
					System.out.println(line);
				}
			}
			linereader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}