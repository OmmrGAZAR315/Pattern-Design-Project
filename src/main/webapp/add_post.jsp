<%@ page import="org.example.dpproject.app.Models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Post</title>
</head>
<body>
<h2>Add Post</h2>
<form action="posts" method="post">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title">
    <input type="hidden" id="userId" name="userId" value="<%= ((User)request.getSession().getAttribute("user")).getId() %>">
    <br><br>
    <label for="content">Content:</label><br>
    <textarea id="content" name="content" rows="4" cols="50"></textarea><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
