<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->        

<jsp:include page="header.html"/>
<html>
    <head>
        <title>Transfer Funds</title>
        <link rel="stylesheet" href="CSS/TOBA.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
        <c:if test="${pageContext.request.getSession(false) != null}">
            <c:if test="${user.getUsername() == null}">
            <h5>J Smith</h5>
            </c:if>
            <c:if test="${user.getUsername() != null}">
                <h5>${user.getFirstName()} ${user.getLastName()}!</h5>
            </c:if> 
                        <div>
                            <h3>Transfer System</h3>
            <label> Savings Balance</label>
            <span>${savings.balance}</span><br>
            <label> Checking Balance</label>
            <span>${checking.balance}</span><br>
            <br><br><label> Destination </label><br>
            <br>
            <form action="Transaction" method="post">
                Transfer Amount :<br>
                <select name = "destination" id = "destination">
                  <option value="checking">Savings to Checking</option>
                  <option value="savings">Checking to Savings</option>
                </select>
                <input type="text" name ="amount">
                <br>
                <input type="submit" value="Transfer">
            </form>
            </c:if>
                    <a href="Account_activity.jsp">Back to Account</a>
    </body>
</html>
<jsp:include page="footer.jsp"/>