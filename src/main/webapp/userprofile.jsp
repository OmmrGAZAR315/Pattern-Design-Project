<%@ page import="org.example.socialmedia_proxy.Model.UserProfile" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 5/7/2024
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="protectPage.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.socialmedia_proxy.Model.UserProfile" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>UserProfile</title>
</head>
<body>
<%
    // Fetch user profile from session
    Object user = request.getSession().getAttribute("user");
    UserProfile userProfile = null;
    if (user != null) {
        userProfile = (UserProfile) user;
    }




%>



<h2>User Profile:</h2>
<p>Username: <%= userProfile.getUsername() %></p>
<p>Password: <%= userProfile.getPassword() %></p>




<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Content</th>
        <!-- Add more table headers as needed -->
    </tr>
    </thead>
    <tbody>
    <% List<Map<String, Object>> posts = (List<Map<String, Object>>) request.getAttribute("posts");
        if (posts != null) {
            for (Map<String, Object> post : posts) { %>
    <tr>
        <td><%= post.get("title") %></td>
        <td><%= post.get("content") %></td>

    </tr>
    <% }
    } %>
    </tbody>
</table>

</body>
</html>
