<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <!-- To render all elements normally. -->
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
            <li><a href="login.jsp">Login</a></li>
        </ul>
    </header>
    <main class="center-flex">
        <h1 aria-label="Sign Up"></h1>
        <div class="container center-flex form-div">
            <p>Sign Up</p>
            <form action="signup" id="signupForm" method="post" onsubmit="return validateForm()">
                <input placeholder="Username" type="text" id="username" name="username" required/>
                <input placeholder="Password" type="password" id="password" name="password" required oninput="validatePassword()"/>
                <input placeholder="Confirm Password" type="password" id="confirmPassword" name="confirmPassword" required oninput="validatePassword()"/>
                <input placeholder="Name" type="text" id="name" name="name" required/>
                <input placeholder="Age" type="number" id="age" name="age" required>
                <div id="passwordError" style="color: red; font-size: 13px;"></div>
                <input type="submit" value="Sign Up">
            </form>
        </div>
    </main>
</div>
</body>
<script src="confirmPassword.js"></script>
</html>
