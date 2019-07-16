Project Purpose:
Create a project to execute SQL statments to query database.

Given HyperSQL files:
	runServer.bat 
	books.properties
	books.script

Procedure:
Save runServer.bat in hsqldb\bin folder. 

Save books.properties and books.script files in the hsqldb\data folder.

In command line, navigate to the hsqldb\bin folder and run command:
	runServer.bat --database.0 file:books --dbname.0 books

In file explorer, navigate to hsqldb\lib and run hsqldb.jar file and enter the following values into the fields:
	Setting Name: books
	Type: HSQL Database Engine Server
	Driver: org.hsqldb.jdbcDriver
	URL: jdbc:hsqldb:hsql://localhost/books
	User: SA
	Password: <leave as blank>
This will allow you to test queries prior to coding, and allow you to test coding for expected results. 

Place a copy of the hsqldb.jar file in java project libary. Right click on the java project and choose the following:
	Properties
	Java Build Path from the left navigation
	Libraries tab
	Click on the Add JARs Button
	Expand folders to the lib folder.
	Choose the hsqldb.jar file
	Click OK to add the jar
	Click OK to close the pop-up

Create a POJO for each table/entity.
Create a DAO for each table/entity to connect to a database and to perform CRUD operations.