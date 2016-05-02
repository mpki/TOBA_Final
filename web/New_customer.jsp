<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<jsp:include page="header.html"/>
<html>
    <head>
        <title>Create new Customer</title>
        <link rel="stylesheet" href="CSS/TOBA.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        
        <div>
            <br>
            <form action="NewCustomerServlet" method="post">
                First Name :<br>
                <input type="text" name ="Firstname">
                <br>
                Last Name :<br>
                <input type="text" name="Lastname">
                <br>
                Phone :<br>
                <input type="text" name="Phone">
                <br>
                Address :<br>
                <input type="text" name="Address">
                <br>
                City :<br>
                <input type="text" name="City">
                <br>
                State :<br>
                <input type="text" name="State">
                <br>
                Zip Code :<br>
                <input type="text" name="Zipcode">
                <br>
                Email :<br>
                <input type="text" name="Email">
                <br>
                <input type="submit" value="Submit">
            </form>
            <br>
        </div>
    </body>
</html>
<jsp:include page="footer.jsp"/>