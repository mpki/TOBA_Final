<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<jsp:include page="header.html"/>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="CSS/TOBA.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <div>
            <form action = "Login">
                Login :<br>
                <input type="text" name ="Username">
                <br>
                Password :<br>
                <input type="password" name="Password">
                <br>
                <input type="Submit" name="Login" value = "Log In">
            </form>
            <br>
            <a href = "password_reset.jsp">Reset Password</a>
            <br>
            <a href="New_customer.jsp">New Customer?</a>
        </div>
    </body>
</html>
<jsp:include page="footer.jsp"/>