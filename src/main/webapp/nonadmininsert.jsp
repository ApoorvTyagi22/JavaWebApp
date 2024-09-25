<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">

<title>HomePage</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class= "InfoBox">
  				<h2> Enter the data you want to store</h2>
 				
 			<%@page import="java.util.Optional" %>	
 			<%@page import="com.testdemo.entities.UserInformMessage" %>	

				<%
				UserInformMessage IncorrectInput = (UserInformMessage)session.getAttribute("Displayed_Message_Usw");
				if(!Optional.ofNullable(IncorrectInput).isEmpty()){
				String DisplayMessage = IncorrectInput.getMessage();
					
				%>
								<div class="alert alert-danger">
   							<h1 class = "popupmessage"> <%= DisplayMessage %> </h1>
								</div>
				<%
				session.removeAttribute("Displayed_Message_Usw");
					}
				%>
   <form action="./InsertData" method="post">
  					<input type="hidden" name="Insert_typeuser" value= "nonadmininsertpg">
  
        <p>Customer ID:</p> 
      
        <input  type="number" name="Personal_id" min="0" required/>
        <br/>
        <p>Customer Name:</p> 
        <input type="text" name="User_name"/>
        <br/>
        <p>Customer Contact Number:</p> 
         
        <input type="number" name="contactNumber" min="0"/>
  <br/>
       <small>(start with 0)</small> 	
       	<p>Customer Address:</p> 
		<input type="text" name="UserAdress"/>
         <br/>
        
        <br/>
        <input type="submit" class="SubmitButton"/>
        
       <p><a href="nonadminread.jsp" class="urlButton" >View Customer </a> 
       <a href="homepagenonadmin.jsp" class="urlButton" >Homepage</a> 
       </p>
		<br/>
       
          <p><a href="index.jsp"class="urlButton">LogOut </a> 
    </form>
 </div>   			
</body>
</html>