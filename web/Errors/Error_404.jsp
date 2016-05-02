<% response.setStatus(404); %>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 Error</title>
    </head>
    <body>
<%
   response.sendError(404, "There seems to have been an Error!" );
%>
    </body>
</html>
