<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page 3</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class= "InfoBox">

					<h2>On this page you can delete the information about your Customers</h2>
					<%@page import="java.util.Optional" %>	
 			  			<%@page import="com.testdemo.entities.UserInformMessage" %>	
 			 
				<%
 				UserInformMessage IncorrectInput = (UserInformMessage)session.getAttribute("Displayed_Message_Usw");
				if(!Optional.ofNullable(IncorrectInput).isEmpty()){
					String DisplayedMessage = IncorrectInput.getMessage();
					
				%>
								<div class="alert alert-danger">
   							<h2 class = "popupmessage"> <%= DisplayedMessage %> </h2>
								</div>
				<%
				session.removeAttribute("Displayed_Message_Usw");
					}
				%>
			
		<form action="./DeleteData" method="post">
		
				 <p>Enter the ID you want to delete:</p> 
				 <input type="number" name="DeleteID" min="0" required/>
        <br/>
		<br/>
		<br/>
        <input type="submit" class="SubmitButton"/>

 <p>
 <a href="Read.jsp" class="urlButton" >View Customer </a> 
 <a href="Main.jsp" class="urlButton">Create Customer  </a>
 <a href="Update.jsp"class="urlButton">Update Customer</a>
 <a href="homepage.jsp" class="urlButton" >Homepage</a> 
 
   </p>
		<br/>
    <p> <a href="index.jsp"class="urlButton">LogOut </a> 

	 </form>
	</div> 
</body>
</html>