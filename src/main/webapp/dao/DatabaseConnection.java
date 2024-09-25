package com.testdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
 static String URL = "jdbc:mysql://localhost:3306/"; 
 static String Name_Of_Database = "testdb";
 static String DatabaseUsername = "root";
 static String DatabasePassword = "root1234";
 static String DriverDatabase = "com.mysql.cj.jdbc.Driver";

	public static  Connection initializeDatabase() 
	        throws SQLException, ClassNotFoundException {
try {
			Class.forName(DriverDatabase);
			 
		}catch(ClassNotFoundException  e) {
			e.printStackTrace();
		}
	        Connection dbconnectmysql = DriverManager.getConnection
	        		(URL +
	        		Name_Of_Database, 
	        		DatabaseUsername,  
	        		DatabasePassword);
	        System.out.println("Connection established: " + !dbconnectmysql.isClosed());       
	        return dbconnectmysql;

	}
}


