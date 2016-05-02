<%@page import="toba.beans.User"%>
<%@ page session="true" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<jsp:include page="header.html"/>
<html>
    <head>
        <title>Congratulations!</title>
        <link rel="stylesheet" href="CSS/TOBA.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div><h3>The Account has been created Successfully!</h3></div>
        <div>
            <label> Username:</label>
            <span>${user.getUsername()}</span><br>
            <label> Password:</label>
            <span>${user.getPassword()}</span><br><br>
            <label> First Name:</label>
            <span>${user.getFirstName()}</span><br>
            <label> Last Name:</label>
            <span>${user.getLastName()}</span><br><br>
            <label> Email:</label>
            <span>${user.getEmail()}</span><br>
            <label> Address:</label>
            <span>${user.getAddress()}</span><br>
            <label> Phone:</label>
            <span>${user.getPhone()}</span><br><br>
            <label> State:</label>
            <span>${user.getState()}</span><br>
            <label> City:</label>
            <span>${user.getCity()}</span><br>
            <label> Zip Code:</label>
            <span>${user.getZip()}</span><br>
            
            
        </div>
    </body>
</html>
<jsp:include page="footer.jsp"/>