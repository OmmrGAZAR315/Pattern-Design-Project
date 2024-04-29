<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.socialmedia_proxy.Model.UserProfile" %>
<%@ page import="java.util.Map" %>
<jsp:include page="protectPage.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        .no-profiles {
            margin-top: 50px;
            text-align: center;
            color: #db0909;
        }

        .right {
            float: right;
            margin-right: 10px; /* Adjust as needed */
        }
    </style>
</head>
<body>
<%
    Object cacheObj = request.getSession().getAttribute("users");
    if (cacheObj instanceof Map<?, ?>) {
        Map<Integer, UserProfile> userProfiles = (Map<Integer, UserProfile>) cacheObj;
%>
<h2>User Profiles</h2>
<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <% if (!userProfiles.isEmpty()) {
        for (Map.Entry<Integer, UserProfile> entry : userProfiles.entrySet()) {
            UserProfile userProfile = entry.getValue();
    %>
    <tr>
        <td><%= entry.getKey() %>
        </td>
        <td><%= userProfile.getUsername() %>
        </td>
        <td><%= userProfile.getPassword() %>
        </td>
        <td><%= userProfile.getName() %>
        </td>
        <td><%= userProfile.getAge() %>
        </td>
    </tr>
    <% }
    } %>
</table>

<% } else { %>
<h1 class="no-profiles">No user profiles found.</h1>
<% } %>
<div class="right">
    <a href="user_ids.jsp">Add a User</a>
    <a href="index.jsp">Back to Home</a>
    <a href="logout">Logout</a>
</div>
</body>
</html>
