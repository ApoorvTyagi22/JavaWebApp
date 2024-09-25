package com.testdemo.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testdemo.dao.DatabaseConnection;
import com.testdemo.entities.UserInformMessage;

@WebServlet("/DeleteInfoController")
public class DeleteInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	    response.setContentType("text/html");
 	    
 	   int DCustomerID = 0;
       
       DCustomerID = Integer.parseInt(request.getParameter("DeleteID"));
       
        try {
        	//connects with the database
        	  Connection deletecusiddbcon = DatabaseConnection.initializeDatabase();
        	  
             // a select query to first check weather the id exists in the database 
             String CheckPrimaryKeyQuery =" SELECT * FROM Data_table WHERE CustomerID = ?";
             PreparedStatement  checkpkpstmt = deletecusiddbcon.prepareStatement(CheckPrimaryKeyQuery);
             // Assigning  the value that will replace the question mark  
             checkpkpstmt.setInt    (1, DCustomerID);
             
             ResultSet fetchedDeleteID = checkpkpstmt.executeQuery();
             // if the value exists than the following if statement is executed 
             if(fetchedDeleteID.next()) {

            
        	 Connection deletecon = DatabaseConnection.initializeDatabase();
           // creating a delete query to remove the id from the table 
            String DeleteSQLquery = "delete from Data_table WHERE CustomerID = ?";	    
			
            PreparedStatement DeletepreparedStmt = deletecon.prepareStatement(DeleteSQLquery);
            // Assigning the variable to replace the question mark 	
            DeletepreparedStmt.setInt(1, DCustomerID);            
            DeletepreparedStmt.executeUpdate();
		    response.getWriter().println("Record with id " + DCustomerID + " has been deleted.");     
			UserInformMessage Displayed_Message_Usw = new UserInformMessage();
        	Displayed_Message_Usw.setMessage("The user has been deleted.");
    		HttpSession apt = request.getSession();
    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
		    response.sendRedirect("Delete.jsp");
		    
		    // if the ID does not exist than the following lines of code are run
             } else {
    	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
    	        	// setting the message 
    	        	Displayed_Message_Usw.setMessage("This ID does not belong to a customer, please enter a valid ID.");
    	    		HttpSession ertsession = request.getSession();
    	    		ertsession.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
    	    		// redirects the user to this page and displays the messsage there 
    	    		response.sendRedirect("Delete.jsp");  
              }

		}
		 catch (Exception e) {
	            e.printStackTrace();
	        }
 	}

}
