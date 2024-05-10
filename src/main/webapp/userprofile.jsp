<jsp:include page="protectPage.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.dpproject.app.Models.UserProfile" %>
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
