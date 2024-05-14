<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="CSS\normalization.css">
    <!-- Google Fonts -->
    <link rel="stylesheet" href="CSS\fonts.css">
    <!-- Font Awesome Library -->
    <link rel="stylesheet" href="CSS\all.min.css">
    <!-- Main template CSS file -->
    <link rel="stylesheet" href="CSS\main.css">
    <link rel="stylesheet" href="CSS\Form.css">
</head>
<body>
<header>
    <h1>Forum</h1>
    <ul>
        <li><a href="home.jsp">Home</a></li>
        <li><a href="signup.jsp">Sign Up</a></li>
    </ul>
</header>
<main class="center-flex">
    <h1 aria-label="Login"></h1>
    <div class="container center-flex form-div">
        <p>Login</p>
        <form action="login" method="post">
            <input placeholder="Userame" type="text" id="username" name="username" required>
            <input placeholder="Password" type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>
        <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
    </div>
</main>
<script src="getAllPosts.js"></script>
</body>
</html>
