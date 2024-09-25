<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update page</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class= "InfoBox">

		<h2>	On this page you can update the information about your Customers</h2>
				<%@page import="java.util.Optional" %>	
 			  			<%@page import="com.testdemo.entities.UserInformMessage" %>	
 							
 											
				<%
				

				UserInformMessage IncorrectInput = (UserInformMessage)session.getAttribute("Displayed_Message_Usw");
				if(!Optional.ofNullable(IncorrectInput).isEmpty()){
					String MessageTOdisplay = IncorrectInput.getMessage();
					
				%>
								<div class="alert alert-danger">
   							<h1 class = "popupmessage"> <%= MessageTOdisplay %> </h1>
								</div>
				<%
				session.removeAttribute("Displayed_Message_Usw");
					}
				%>
						<form action="./UpdateData" method="post">
			
				
				
					<p>Enter the CustomerID you want to update:</p>
				 <input type="number" name="CustomerIDtoUpdate" min="0" required/>
				 	<br/>
					<p>Enter the new CustomerName:</p>
				 <input type="text" name="NewUserName" />
		<br/>
					<p>Enter the new Contact:</p>
				 <input type="text" name="NewUserContact" min="0" />
				  <br/>
       <small>(start with 0)</small> 
		<br/>
					<p>Enter the new Address:</p>
				 <input type="text" name="NewUserAddress" />
		<br/>				 
			        			   		<br/>
		
			       <input type="submit" class="SubmitButton" />
			        <p>
			        
			        <a href="Read.jsp" class="urlButton" >View Customer </a> 
			        <a href="Delete.jsp" class="urlButton">Delete Customer </a> 
			        <a href="Main.jsp"class="urlButton">Create Customer</a> 
			         <a href="homepage.jsp" class="urlButton" >Homepage</a> 
			        
		<br/>
		<br/>

			           <p>   <a href="index.jsp"class="urlButton">LogOut </a> 
			        
			        </p>
				
			
			</form>
	</div>	
</body>
</html>