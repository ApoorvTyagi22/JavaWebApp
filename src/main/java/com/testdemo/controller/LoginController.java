package com.testdemo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testdemo.dao.DatabaseConnection;
import com.testdemo.entities.UserInformMessage;
 

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		 
		response.setContentType("text/html");
		
		UserInformMessage Invalid_Input_Mesg = new UserInformMessage();
		String Login_type_login = "";
		String Usr_nme = request.getParameter("Inserted_Use_Name");
		String Pass = request.getParameter("Inserted_Pass_word");

		try {
// connecting with the database 
            Connection Login_Dbconnection = DatabaseConnection.initializeDatabase();

		// select query to check if the loin details exist 
            String Login_check_query = "SELECT Login_Type FROM IA_multiple_loginDetails WHERE UserName = ? and Corresponding_UserName_Password = ?";		
            PreparedStatement LoginPstmt = Login_Dbconnection.prepareStatement(Login_check_query);
            // assigning the variables that will replace the question mark 
			LoginPstmt.setString(1, Usr_nme);
			LoginPstmt.setString(2, Pass);
		
			ResultSet retrivedData = LoginPstmt.executeQuery();
			if (retrivedData.next()) {
				// Retrieving the user type from the database table through the result set
				 Login_type_login = retrivedData.getString("Login_Type");
				 
				 try {		
					 // if type is admin the user is redirected to the admin home page
						if (Login_type_login.equals("admin")) {
							RequestDispatcher correctadmin = request.getRequestDispatcher("homepage.jsp");
							correctadmin.forward(request, response);

						} else if 
						// if not admin than redirected to non-admin home page 
							(Login_type_login.equals("non") ) {

							RequestDispatcher correctnonadmin = request.getRequestDispatcher("homepagenonadmin.jsp");
							correctnonadmin.forward(request, response);

						}
					}catch(Exception p) {
						p.printStackTrace();		
						}
				 
				
			} else {
				Invalid_Input_Mesg.setMessage("Invalid input: The username or password entered is incorrect please try again.");
				HttpSession s = request.getSession();
				s.setAttribute("InvalidInputMessage", Invalid_Input_Mesg);
				response.sendRedirect("index.jsp");

			}
		}
		catch(Exception e) {
				e.printStackTrace();
		}
		 	

}
	}

