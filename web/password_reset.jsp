<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.html"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<form action="ResetPass" method="post">
                User Name :<br>
                <input type="text" name="Username"> <br>
                Current Password :<br>
                <input type="text" name="Currentpass"> <br>
                New Password :<br>
                <input type="text" name="Newpass"> <br>
                <input type="submit" value="Submit">
            </form>
    </body>
</html>
<jsp:include page="footer.jsp"/>