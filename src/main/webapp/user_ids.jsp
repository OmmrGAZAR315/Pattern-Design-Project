<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="db_connection.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile Form</title>
</head>
<body>
<h2>User Profile Form</h2>
<%
    if (request.getSession().getAttribute("error") != null) {
        response.setContentType("text/html;charset=UTF-8");
%>
<div style="color: red;">
    <%= request.getSession().getAttribute("error").toString() %>
</div>
<%
        request.getSession().removeAttribute("error");
    }
%>
<form action="userProfile" method="get">
    <label for="userId">Enter User ID:</label>
    <input type="text" autofocus id="userId" name="userId">
    <input type="submit" value="Submit">
</form>
</body>
</html>
