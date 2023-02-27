<%-- 
    Document   : register
    Created on : 25-Feb-2023, 3:58:58 AM
    Author     : Mitchell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p name="message">${message}</p>
        <form method="post" action="?action=register">
          <label for="username">Username:</label>
            <input type="text" name="username" id="username"><br>
            <br>
          <input type="submit" value="Register name">
        </form>
        
        <p style="color:red;">${errorMessage}</p>
    </body>
</html>
