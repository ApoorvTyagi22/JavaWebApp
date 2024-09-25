<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class= "InfoBox">

				
											<h2>LogIn</h2>
											<p>Enter your credentials</p> 
 							<%@page import="java.util.Optional" %>	
 											
				<%
				

				UserInformMessage Invalid = (UserInformMessage)session.getAttribute("InvalidInputMessage");
				if(!Optional.ofNullable(Invalid).isEmpty()){
					String DisplayedMessage = Invalid.getMessage();
					
				%>
								<div class="alert alert-danger">
   							<h2 class = "popupmessage"> <%= DisplayedMessage %> </h2>
								</div>
				<%
				session.removeAttribute("InvalidInputMessage");
					}
				%>
 					<form action=./UserLogin method="post">
					<p>Enter the UserName:</p>
		
				 	<input type="text" id= "UserName"name="Inserted_Use_Name"/>
  	 	 <br/>
 					<p>Enter the Password:</p>
					<input type="password" name="Inserted_Pass_word"/>
					 <br/>
					 <br/>
					<br/>
					<input type="submit" class="SubmitButton"/>
					
		<%@page import="com.testdemo.entities.UserInformMessage" %>	
				<%@page import="java.util.Optional" %>	
		
    </form>
</div> 
</body>
</html>




	
				