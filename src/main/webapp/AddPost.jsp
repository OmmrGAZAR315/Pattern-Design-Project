<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 4/29/2024
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Add Post</h1>
<form action="addPost" method="post">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>

    <label for="content">Content:</label><br>
    <textarea id="content" name="content"></textarea><br>

    <!-- You may hide this field if the user is already logged in and you have their ID -->
    <input type="hidden" id="userId" name="userId" value="1"><br>

    <button type="submit">Submit</button>
</form>
</body>
</html>
