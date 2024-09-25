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
 
 @WebServlet("/CreateInfoController")
public class CreateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Fetching user input from the JSP file and transferring it to defined variables 
		Integer idtoadd =  (Integer.parseInt(request.getParameter("Personal_id")));
        String custNametoadd = (request.getParameter("User_name"));
        String contactNumber =  request.getParameter("contactNumber");
        String cusaddresstoadd = request.getParameter("UserAdress");
        String User_type = request.getParameter("Insert_typeuser");
        
        if (User_type.equals("admininsertpg")) {
        	
        	
        if( custNametoadd != ""  && cusaddresstoadd != ""  && contactNumber.startsWith("0")) {
		try {
			 
            // Initializing a Connection with the database
            Connection con = DatabaseConnection.initializeDatabase();
  
          //creating a check primary key query to verify if the ID already exists in the database 
           String CheckPrimaryKeyQuery =" SELECT * FROM Data_table WHERE CustomerID = ?";

           PreparedStatement  CheckPStmt = con.prepareStatement(CheckPrimaryKeyQuery);
           // setting the variable that will replace the question mark 
           CheckPStmt.setInt    (1, idtoadd);
           ResultSet FInt = CheckPStmt.executeQuery();
           
           if(!FInt.next()) {
               // if there are no duplicates for the primary key than a create query is executed to insert data in the database table

            
            String Createquery = "insert into Data_table (CustomerID, CustomerName, Contact, Address) values (?, ?, ?, ?)";
            PreparedStatement insertpreparedstmt = con.prepareStatement(Createquery);
            insertpreparedstmt.setInt    (1, idtoadd);
            insertpreparedstmt.setString (2, custNametoadd);
            insertpreparedstmt.setString (3, contactNumber);
            insertpreparedstmt.setString (4, cusaddresstoadd);

            insertpreparedstmt.execute();
            
  
            insertpreparedstmt.close();
            
            con.close();

             // creating an object of UserInformMessage 
        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
        	// setting the message to be displayed
        	Displayed_Message_Usw.setMessage("Sucessfully inserted.");
    		HttpSession apt = request.getSession();
    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
    		// redirects the user to the following page and displays the message there 
    		response.sendRedirect("Main.jsp");
           } else {
  	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
  	        	Displayed_Message_Usw.setMessage("This id is already assigned to a customer.");
  	    		HttpSession apt = request.getSession();
  	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
  	    		response.sendRedirect("Main.jsp");  
            }
           
 		}
		 catch (Exception e) {
 	        }
 	} 	 else {
 		UserInformMessage Displayed_Message_Usw = new UserInformMessage();
 		Displayed_Message_Usw.setMessage("Invalid Input: Please enter data in all fields and for contact start with 0.");
		HttpSession apt = request.getSession();
		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
		response.sendRedirect("Main.jsp"); 		
 	}
        
        }  
    

        else if (User_type.equals("nonadmininsertpg")) {
 
			if( custNametoadd != null &&  cusaddresstoadd != null  &&contactNumber.startsWith("0")) {
        			try {
        				  
        				System.out.println("Inside doPost :::");
        	            // Initialize the database connection
        	            Connection con = DatabaseConnection.initializeDatabase();
        	  
        	         // check query for primary key
        	            
        	            String CheckPrimaryKeyQuery =" SELECT * FROM Data_table WHERE CustomerID = ?";
        	            PreparedStatement  CheckPStmt = con.prepareStatement(CheckPrimaryKeyQuery);
        	            CheckPStmt.setInt    (1, idtoadd);
        	            ResultSet FInt = CheckPStmt.executeQuery();
        	            
        	            if(!FInt.next()) {
          	            
        	            String Createquery = "insert into Data_table (CustomerID, CustomerName, Contact, Address) values (?, ?, ?, ?)";
        	            PreparedStatement insertpreparedstmt = con.prepareStatement(Createquery);
        	            insertpreparedstmt.setInt    (1, idtoadd);
        	            insertpreparedstmt.setString (2, custNametoadd);
        	            insertpreparedstmt.setString (3, contactNumber);
        	            insertpreparedstmt.setString (4, cusaddresstoadd);

        	            insertpreparedstmt.execute();
        	            
        	  
        	            insertpreparedstmt.close();
        	            
        	            con.close();
        	  
        	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
        	        	Displayed_Message_Usw.setMessage("Sucessfully inserted.");
        	    		HttpSession apt = request.getSession();
        	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
        	    		response.sendRedirect("nonadmininsert.jsp");        	            
        	            } else {
        	            	// creating an object 
        	            	   UserInformMessage Displayed_Message_Usw = new UserInformMessage();
        	            	   // setting the message 
               	        	Displayed_Message_Usw.setMessage("This id is already assigned to a customer.");
               	    		HttpSession apt = request.getSession();
               	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
               	    		// redirects the user to the following web page and displays the message set 
               	    		response.sendRedirect("nonadmininsert.jsp");   
        	            }	
         
        			} catch (Exception inserterror) {
        		            inserterror.printStackTrace();
        		            
        		                  
        	 	} }	 else {
        	 		UserInformMessage Displayed_Message_Usw = new UserInformMessage();
        	 		Displayed_Message_Usw.setMessage("Invalid Input: Please enter data in all fields and for contact start with 0.");
        			HttpSession apt = request.getSession();
        			apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
        			response.sendRedirect("nonadmininsert.jsp"); 		
        	 	}	
        }    
	}}

