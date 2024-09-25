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
			
 	<form action="./SearchData" method="post">
 					<input type="hidden" name="readtypeuser" value= "nonadminreadpg">
 	
	<p> Please Select a valid option </p>
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
	
	  <p><a href="nonadmininsert.jsp" class="urlButton" >Create Customer</a>
	  <a href="homepagenonadmin.jsp" class="urlButton" >Home Page</a>
	  
	  </p>
		<br/>
	  
   <p>   <a href="index.jsp"class="urlButton">LogOut </a> 
</p>
 	</form>
 	</div>
</body> 
</html>
