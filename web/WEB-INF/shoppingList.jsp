<%-- 
    Document   : shoppingList
    Created on : 25-Feb-2023, 3:59:46 AM
    Author     : Mitchell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>${welcomeMessage}</p>
        <a href="?action=logout">Log out</a>
        <h2>List</h2>
        <p style="color:red;">${errorMessage}</p>
        <form method="post" action="?action=addItem">
            <label for="username">Add item:</label>
            <input type="text" name="addItem" id="addItem">
            <input type="submit" value="Add">
            <br>
            <br>
            </form>
        <form method="post" action="?action=delete">
            <c:forEach var="item" items="${items}">
                <input type="radio" name="selectItem" id="selectItem" value="${item}">
                <label for="selectItem">${item}</label><br>
            </c:forEach>
            <c:if test="${count > 0}">
                <input type="submit" value="Delete">
            </c:if>
        </form>

    </body>
</html>
