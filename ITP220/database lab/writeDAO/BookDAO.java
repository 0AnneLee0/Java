package writeDAO;
//DAO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDAO {
	// URL - need URL to connect database
	private static final String DATABASE_URL = 
			"jdbc:hsqldb:hsql://localhost/books";

	// id and password to access database
	// not hard coded in real world 
	private static final String ID = "SA";  
	private static final String PASSWORD = ""; 

	// SQL we want to run  
	private final static String SELECT_ALL_BOOKS =    
			//"SELECT isbn, title, edition_number, copyright "
			"SELECT * FROM titles ORDER BY title ASC";

	//--- SQL Lab 19 Code ----
	private final static String SELECT_WHERE =
			"SELECT * FROM titles WHERE copyright > ? ORDER BY title ASC";

	private final static String CREATE_BOOK = 
			"INSERT INTO titles (isbn, title, edition_number, copyright)" + 
					"VALUES (?, ?, ?, ?)";

	private final static String UPDATE_BOOK =
			"UPDATE titles SET edition_number = ?" + 
					"WHERE title = ? AND isbn = ?";

	private final static String DELETE_BOOK =
			"DELETE FROM titles WHERE title = ? AND isbn = ?";

	
	// numbers for each field
	private final static int ISBN = 1;  
	private final static int TITLE = 2;  
	private final static int EDITION_NUM = 3;  
	private final static int COPYRIGHT = 4; 


	public static List<Book> getBooks(){
		// by not passing content into getBooks() 
		// allows for overloading and gets all data

		//-- create empty ArrayList --
		List<Book> listOfBooks = new ArrayList<>();

		try {
			//-- get connection to DB --
			// using URL, ID, and PW
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);

			// create a statement that we plan to use    
			Statement statement = connection.createStatement();        

			//-- execute a SQL query against DB --
			// when communicating with DB, returns data in form of resultSet
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_BOOKS);

			String isbn;    
			String title;    
			Integer editionNumber;    
			String copyright;    
			Book book;

			// get each object, which is an individual book
			while(resultSet.next()) {
				// get each column, which are the attributes for each book     
				// order is based on the order specified in the query 
				isbn = resultSet.getString(ISBN);     
				title = resultSet.getString(TITLE);     
				editionNumber = resultSet.getInt(EDITION_NUM);     
				copyright = resultSet.getString(COPYRIGHT); 

				// create a book object - parameters must match constructor in POJO     
				book = new Book(isbn, title, editionNumber, copyright); 

				// add the book object to the list     
				listOfBooks.add(book); 
			}
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		//-- return list --
		return listOfBooks;
	}

	public static List<Book> getBooks (String copyR){
		List<Book> listOfBooks = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);
			PreparedStatement selectWhere = connection.prepareStatement(SELECT_WHERE);
			selectWhere.setString(1, copyR);
			ResultSet resultSet = selectWhere.executeQuery();

			String isbn;    
			String title;    
			Integer editionNumber;    
			String copyright;    
			Book book;

			while(resultSet.next()) {
				isbn = resultSet.getString(ISBN);     
				title = resultSet.getString(TITLE);     
				editionNumber = resultSet.getInt(EDITION_NUM);     
				copyright = resultSet.getString(COPYRIGHT); 

				book = new Book(isbn, title, editionNumber, copyright); 

				listOfBooks.add(book); 
			}
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		//-- return list --
		return listOfBooks;
	}

	public static void addBook (String isbn, String title, int edNum, String copyR) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);
			PreparedStatement createBook = connection.prepareStatement(CREATE_BOOK);

			//set parameters
			createBook.setString(1, isbn);
			createBook.setString(2, title);
			createBook.setInt(3, edNum);
			createBook.setString(4, copyR);

			createBook.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public static void updateBook (String in_title, String in_isbn, int edNum) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);
			PreparedStatement updateBook = connection.prepareStatement(UPDATE_BOOK);

			//set parameters
			updateBook.setInt(1, edNum);
			updateBook.setString(2, in_title);
			updateBook.setString(3, in_isbn);

			updateBook.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public static void deleteBook (String title, String isbn) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, ID, PASSWORD);
			PreparedStatement deleteBook = connection.prepareStatement(DELETE_BOOK);

			//set parameters
			deleteBook.setString(1, title);
			deleteBook.setString(2, isbn);

			deleteBook.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}


	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		String option = "Y";
		String copyright, isbn, title;
		int edNumber = 0;
		
		//--- GET ALL BOOKS ---
		System.out.println("Do you want a list of all books in the database? (Y/N):");
		option = input.next();
		if(option.equalsIgnoreCase("Y")) {
			List<Book> allBooks = getBooks();
			System.out.println("Our array list of books is" + allBooks);
			System.out.println("Our array list of books is");
			for (Object item: allBooks) {
				System.out.printf("%s\n",item);		
			}
		}

		//--- GET BOOKS AFTER YEAR ---
		System.out.println("\n\nDo you want to look up books after a specific year? (Y/N):");
		option = input.next();
		if(option.equalsIgnoreCase("Y")) {
			System.out.println("Enter year to retrieve books after that year:");
			copyright = input.next(); 
			List<Book> booksAfter2013 = getBooks(copyright);
			System.out.println("Our array list of books after " + copyright);
			for (Object item: booksAfter2013) {
				System.out.printf("%s\n",item);		
			}
		}

		//--- ADD NEW BOOK ENTRY ---
		System.out.println("\n\nDo you want to enter a new book entry? (Y/N):");
		option = input.next();
		if(option.equalsIgnoreCase("Y")) {
			System.out.println("Enter isbn for new book entry: ");
			isbn = input.next(); 
			System.out.println("Enter title for new book entry: ");
			title = input.next(); 
			System.out.println("Enter editon number for new book entry: ");
			edNumber = input.nextInt();
			System.out.println("Enter copyright for new book entry: ");
			copyright = input.next(); 
			addBook(isbn, title, edNumber, copyright);
		}

		//--- UPDATE BOOK ENTRY ---
		System.out.println("\n\nDo you want to update edition number for a book entry? (Y/N):");
		option = input.next();
		if(option.equalsIgnoreCase("Y")) {
			System.out.println("Enter title of book to update: ");
			title = input.next();
			System.out.println("Enter isbn of book to update: ");
			isbn = input.next(); 
			System.out.println("Enter new edition number: ");
			edNumber = input.nextInt();
			updateBook(title, isbn, edNumber);
		}

		//--- DELETE BOOK ENTRY ---
		System.out.println("\n\nDo you want to delete a book entry? (Y/N):");
		option = input.next();
		if(option.equalsIgnoreCase("Y")) {
			System.out.println("Enter title of book to update: ");
			title = input.next();
			System.out.println("Enter isbn of book to update: ");
			isbn = input.next(); 
			deleteBook(title, isbn);
		}
		input.close();
	}
}