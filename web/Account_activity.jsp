<%@page import="toba.beans.User"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
        <title>Account Page</title>
        <link rel="stylesheet" href="CSS/TOBA.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
            <c:if test = "${user.getTransactionsAmount() > 0}">

            <sql:setDataSource
                var="DS"
                driver="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/toba?zeroDateTimeBehavior=convertToNull"
                user="root" password="sesame"
            />
            <sql:query var="ListTransactions" dataSource="${DS}">
                SELECT * FROM transactions WHERE Username = '${user.getUsername()}';
            </sql:query>
            </c:if>
        <div>
            <c:if test="${user.getUsername() != null}">
                <h5>Hello ${user.getFirstName()} ${user.getLastName()}!</h5>
                <h4>Login Successful!</h4> 
                        <div>
                            <h3> Account Information </h3>
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
            
            <h3> Transaction Information </h3>
            <a href="TransferSystem.jsp">Transfer Funds</a>

            <c:if test = "${user.getTransactionsAmount() > 0}">
                
            
        <table border="1" cellpadding="5">
            <caption><h4>Transactions</h4></caption>
            <tr>
                <th>Username</th>
                <th>Source</th>
                <th>Destination</th>
                <th>Transferred</th>
                <th>Date</th>
            </tr>
            <c:forEach var="t" items="${ListTransactions.rows}">
                <tr>
                    <td><c:out value="${t.Username}" /></td>
                    <td><c:out value="${t.Source}" /></td>
                    <td><c:out value="${t.Destination}" /></td>
                    <td><c:out value="${t.Transfer}" /></td>
                    <td><c:out value="${t.Date}" /></td>
                </tr>
            </c:forEach>
        </table>
            </div>
            </c:if>
        </c:if>
        <c:if test = "${pageContext.request.getSession(false) == null}">
        <h5>Not logged in</h5>
        </c:if>
        </div>
    </body>
</html>
<jsp:include page="footer.jsp"/>