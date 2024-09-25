package com.testdemo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testdemo.dao.DatabaseConnection;
import com.testdemo.entities.UserInformMessage;

@WebServlet("/UpdateInfoController")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	    response.setContentType("text/html");
 	      Integer Updateth_id = 0;
          String Updateth_name = null;
          String Updateth_contact = null;
          String Updateth_address = null;
  		 Updateth_id = Integer.parseInt(request.getParameter("CustomerIDtoUpdate"));
         Updateth_name = request.getParameter("NewUserName");
         Updateth_contact = request.getParameter("NewUserContact");
         Updateth_address = request.getParameter("NewUserAddress");
         
         // validates and then only executes the if statement 
         if( Updateth_name != "" && Updateth_address != ""  &&Updateth_contact.startsWith("0")) {


 		  try {
 	            Connection checkpkconnection = DatabaseConnection.initializeDatabase();
 			  // checking is the ID exists in the primary key column 
 			 String CheckPrimaryKeyQuery =" SELECT * FROM Data_table WHERE CustomerID = ?";

 	           PreparedStatement  CheckPStmt = checkpkconnection.prepareStatement(CheckPrimaryKeyQuery);
 	           // defining the variable that will replace the question mark in the CheckPrimaryKeyQuery
 	           CheckPStmt.setInt    (1, Updateth_id);
 	           ResultSet FInt = CheckPStmt.executeQuery();
 	           
 	           if(FInt.next()) {

 	           
	        	 Connection updatecon = DatabaseConnection.initializeDatabase();
	        	 // creating an update query to update customer information in the table 
	             String UpdateSQLTableQuery = "UPDATE  Data_table SET CustomerName= ?, Contact= ?, Address= ?" + "where CustomerID= ?";

 	             PreparedStatement UpdatePstmt = updatecon.prepareStatement(UpdateSQLTableQuery);
 	             
 	            // assigning the variables that will replace the 
 	             //question marks in UpdateSQLTableQuery along with the number that represents its position 
 	            UpdatePstmt.setString (1, Updateth_name);	
 	            UpdatePstmt.setString (2, Updateth_contact);	
 	            UpdatePstmt.setString (3, Updateth_address);	
 	            UpdatePstmt.setInt    (4, Updateth_id);
 	            UpdatePstmt.executeUpdate();
 	            
 	            // Creating a new object of the UserInformMessage
 	       	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
 	       	// setting the message to the text 
        	Displayed_Message_Usw.setMessage("Updated!");
    		HttpSession apt = request.getSession();
    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
    		// redirects and displays the message on the update web page
    		response.sendRedirect("Update.jsp");
 	          } else {
 	    	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
 	    	        	Displayed_Message_Usw.setMessage("This ID does not belong to a customer, please enter a valid ID.");
 	    	    		HttpSession apt = request.getSession();
 	    	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
 	    	    		response.sendRedirect("Update.jsp");  
 	              }
 	}
 			 catch (Exception e) {
		            e.printStackTrace();
		        }
        }  else {
     		UserInformMessage Displayed_Message_Usw = new UserInformMessage();
     		Displayed_Message_Usw.setMessage("Invalid Input: Please enter data in all fields and for contact start with 0.");
    		HttpSession apt = request.getSession();
    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
    		response.sendRedirect("Update.jsp"); 		
     	}
 	}
	}

