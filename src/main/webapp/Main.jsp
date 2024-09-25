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
					String DispladMessage = IncorrectInput.getMessage();
					
				%>
								<div class="alert alert-danger">
   							<h2 class = "popupmessage"> <%= DispladMessage %> </h2>
								</div>
				<%
				session.removeAttribute("Displayed_Message_Usw");
					}
				%>
	
  <form action="./InsertData" method="post">
    					<input type="hidden" name="Insert_typeuser" value= "admininsertpg">
  
        <p>Customer ID:</p> 
  
        <input  type="number" name="Personal_id" min="0" required/>
        <br/>
        <p>Customer Name:</p> 
        <input type="text" name="User_name"/>
        <br/>
        <p>Customer Contact Number :</p>
          <input type="number" name="contactNumber" min="0"/>
        <br/>
       <small>(start with 0)</small> 
        
		<p>Customer Address:</p> 
		<input type="text" name="UserAdress"/>
         <br/>
        
        <br/>
        <input type="submit" class="SubmitButton"/>
        
       <p><a href="Read.jsp" class="urlButton" >View Customer </a> 
       <a href="Delete.jsp" class="urlButton">Delete Customer </a> 
       <a href="Update.jsp"class="urlButton">Update Customer </a>
	 <a href="homepage.jsp" class="urlButton" >Homepage</a> 
</p>
		<br/>
		
          
          <p>   <a href="index.jsp"class="urlButton">LogOut </a> 
       
        
 
    </form>
  </div>  			

</body>
</html>