package writeDAO;
// gets all authors in database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
	// URL - need URL to connect database
	private static final String DATABASE_URL = 
			"jdbc:hsqldb:hsql://localhost/books";

	// id and password to access database
	// not hard coded in real world 
	private static final String ID = "SA";  
	private static final String PASSWORD = "";

	// SQL we want to run  
	private final static String SELECT_ALL_AUTHORS =    
			"SELECT author_id, first_name, last_name FROM authors ORDER BY last_name ASC";

	// numbers for each field
	private final static int AUTHOR_ID = 1;  
	private final static int FIRST_NAME = 2;  
	private final static int LAST_NAME = 3;  

	public static List<Author> getAuthors(){

		//-- create empty ArrayList --
		List<Author> listOfAuthors = new ArrayList<>();

		try {
			//-- get connection to DB --
			// using URL, ID, and PW
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);

			// create a statement that we plan to use    
			Statement statement = connection.createStatement();        

			//-- execute a SQL query against DB --
			// when communicating with DB, returns data in form of resultSet
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_AUTHORS);

			Integer author_id;    
			String first_name;    
			String last_name;       
			Author author;

			// get each object, which is an individual book
			while(resultSet.next()) {
				// get each column, which are the attributes for each book     
				// order is based on the order specified in the query 
				author_id = resultSet.getInt(1);     
				first_name = resultSet.getString(2);     
				last_name = resultSet.getString(3);     

				// create a book object - parameters must match constructor in POJO     
				author = new Author(author_id, first_name, last_name); 

				// add the book object to the list     
				listOfAuthors.add(author); 
			}
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		//-- return list --
		return listOfAuthors;
	}


	public static void main(String[]args) {
		List<Author> allAuthors = getAuthors();
		//System.out.println("Our array list of authors is" + allAuthors);
		System.out.println("Our array list of authors is");
		for (Object item: allAuthors) {
			System.out.printf("%s\n",item);
		}
	}
}
