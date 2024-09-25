<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page2</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class= "InfoBox">

								<h2> View the Customer Details</h2>
									<p> Please Select a valid option </p>
								
							<%@page import="java.util.Optional" %>	
 			<%@page import="com.testdemo.entities.UserInformMessage" %>	
 											
				<%
				

				UserInformMessage Invalid = (UserInformMessage)session.getAttribute("Displayed_Message_Usw");
				if(!Optional.ofNullable(Invalid).isEmpty()){
					String DisplayMessage = Invalid.getMessage();
					
				%>
								<div class="alert alert-danger">
   						<h2 class = "popupmessage"> <%= DisplayMessage %> </h2>
								</div>
				<%
				session.removeAttribute("Displayed_Message_Usw");
					}
				%>
			
	<form action="./SearchData" method="post">
					<input type="hidden" name="readtypeuser" value= "adminreadpg">
	
        <br/>	
 <table>
 		<tr>
 		
 		<td>Enter the ID: </td>
		<td><input type= "number" name="UserRequestedID" required/></td>
		</tr>
		
		<td colspan="2"><input type="submit" value="search" class="SubmitButton"></td>
 </table>
  
 	</form>
</body>
		
<body>
<form action="./SearchData" method="get">

	 <p><td colspan="2">Want to view the entire customer table click here: </td> <td colspan="2"><input type="submit" value="search" class="SubmitButton"></td></p>
	
	  <p><a href="Main.jsp" class="urlButton" >Create Customer</a>
	   <a href="Delete.jsp" class="urlButton">Delete Customer </a>
	    <a href="Update.jsp"class="urlButton">Update Customer </a> 
	     <a href="homepage.jsp" class="urlButton" >Homepage</a> 
	    </p>
		<br/>
	    
   <p>   <a href="index.jsp"class="urlButton">LogOut </a> </p>
   </div>
  
 	</form>

</body> 

</html>

