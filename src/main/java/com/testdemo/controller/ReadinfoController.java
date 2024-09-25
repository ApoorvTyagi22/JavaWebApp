package com.testdemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testdemo.dao.DatabaseConnection;
import com.testdemo.entities.UserInformMessage;

import java.sql.*;
 
@WebServlet("/ReadinfoController")
public class ReadinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		
		response.setContentType("text/html");

		try {
 // connecting with the database
			Connection read_Connection = DatabaseConnection.initializeDatabase();
			           
	          String ReadQuery = ("select * from Data_table" );
	          PreparedStatement P_Read_stmt = read_Connection.prepareStatement(ReadQuery);
	          ResultSet fetchrs  = P_Read_stmt.executeQuery();
	          
	          out.println("<table border=1 width=50% height=50% align=\"center\"<tr bgcolor = \" D6D6D6\">");  
	          out.println("<tr><th>CustomerID</th><th>CustomerName</th><th>Contact</th><th>Address</th><tr>");  
	          	          
				while   (fetchrs.next()) {
				// transferring data from the result set to the variables 	
			int cID =    fetchrs.getInt("CustomerID");
			String cNA = fetchrs.getString("CustomerName");
			String cC =  fetchrs.getString("Contact");
			String cA =  fetchrs.getString("Address");
// printing the data in a table 
					out.println("<tr><td>" + cID + "</td><td>" + cNA + "</td><td>" + cC +"</td><td>" + cA + "</td><tr>");  
				}	
		}catch (SQLException | ClassNotFoundException p) {
			System.out.println(p);
		}
		
			out.print("</table>");
	}
			

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
 	
		PrintWriter out= response.getWriter();
		// setting type to text and html so that tables can be made 
		response.setContentType("text/html");
 		 	Integer ID_TO_DISPALY = 0;
 		 	// taking input from the jsp files 
  			ID_TO_DISPALY = Integer.parseInt(request.getParameter("UserRequestedID"));
  			String User_type = request.getParameter("readtypeuser");
  			
// checking the user trying to execute the function 
  			if (User_type.equals("adminreadpg")) {
 		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
  			String readcustomdetailsquery = "SELECT * FROM Data_table WHERE CustomerID = ?";
  			PreparedStatement readpstmt = con.prepareStatement(readcustomdetailsquery);
  			readpstmt.setInt(1, ID_TO_DISPALY);
  			ResultSet readrs = readpstmt.executeQuery();
			  out.println("<table border=1 width=50% height=50% align=\"center\"<tr bgcolor = \" D6D6D6\">");  
	          out.println("<tr><th>CustomerID</th><th>CustomerName</th><th>Contact</th><th>Address</th><tr>");  
	         boolean found = false; 
	          while (readrs.next())
  			{
  			int customerid = readrs.getInt("CustomerID");
  			if(customerid == ID_TO_DISPALY)
  			{
  				found = true; 
  			String customname = readrs.getString("CustomerName");
  			String CustomerContact = readrs.getString("Contact");
  			String CustomerAdress = readrs.getString("Address");

  			out.print("<tr><td>");
  			out.println(customerid);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(customname);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(CustomerContact);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(CustomerAdress);
  			out.print("</td>");
  			out.print("</tr>");
  			}
   			}	
	        if (!found) {
	        	  
	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
	        	Displayed_Message_Usw.setMessage("User doesn't exist, please try again.");
	    		HttpSession apt = request.getSession();
	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
	    		response.sendRedirect("Read.jsp");
	          }
	        	  
 		}catch (SQLException | ClassNotFoundException p) {
			System.out.println(p);
 		}
 		// if the user is not an admin than its a non-admin user 
	} else if (User_type.equals("nonadminreadpg")){
		try {
			Connection readconnectionnonadmin = DatabaseConnection.initializeDatabase();
  			String Readquery = "SELECT * FROM Data_table WHERE CustomerID = ?";
  			PreparedStatement readpstmt = readconnectionnonadmin.prepareStatement(Readquery);
  			readpstmt.setInt(1, ID_TO_DISPALY);
  			ResultSet rs = readpstmt.executeQuery();
			  out.println("<table border=1 width=50% height=50% align=\"center\"<tr bgcolor = \" D6D6D6\">");  
	          out.println("<tr><th>CustomerID</th><th>CustomerName</th><th>Contact</th><th>Address</th><tr>");  
	         boolean found = false; 
	          while (rs.next())
  			{
  			int customerid = rs.getInt("CustomerID");
  			if(customerid == ID_TO_DISPALY)
  			{
  				found = true; 
  			String name = rs.getString("CustomerName");
  			String CustomerContact = rs.getString("Contact");
  			String CustomerAdress = rs.getString("Address");

  			out.print("<tr><td>");
  			out.println(customerid);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(name);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(CustomerContact);
  			out.print("</td>");
  			out.print("<td>");
  			out.println(CustomerAdress);
  			out.print("</td>");
  			out.print("</tr>");
  			}
   			}	
	        if (!found) {
	        	  
	        	UserInformMessage Displayed_Message_Usw = new UserInformMessage();
	        	Displayed_Message_Usw.setMessage("User doesn't exist, please try again.");
	    		HttpSession apt = request.getSession();
	    		apt.setAttribute("Displayed_Message_Usw", Displayed_Message_Usw);
	    		response.sendRedirect("nonadminread.jsp");
	          }
	        	  
 		}catch (SQLException | ClassNotFoundException p) {
			System.out.println(p);
 		}
		
		
	}
	}

}
