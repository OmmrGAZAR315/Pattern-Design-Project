<jsp:include page="protectPage.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.dpproject.app.Models.User" %>
<%@ page import="org.example.dpproject.app.Models.User" %>
<html>
<head>
    <title>UserProfile</title>
</head>
<body>
<%
    // Fetch user profile from session
    Object user = request.getSession().getAttribute("user");
    User user = null;
    if (user != null) {
        user = (User) user;
    }

%>



<h2>User Profile:</h2>
<p>Username: <%= user.getUsername() %></p>
<p>Password: <%= user.getPassword() %></p>




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
