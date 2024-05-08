<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page import="org.example.socialmedia_proxy.Model.postDao" %>
<%@ page import="org.example.socialmedia_proxy.Model.UserProfile" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="protectPage.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>

    <style>
        /* Example CSS styles for the post container */
        .post-container {
            width: 80%;
            margin: auto;
            border: 1px solid #ccc;
            padding: 20px;
            margin-top: 20px;
        }
        .post {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<a href="userprofile">UserProfile</a>



<%
    // Fetch user profile from session
    Object user = request.getSession().getAttribute("user");
    UserProfile userProfile = null;
    if (user != null) {
        userProfile = (UserProfile) user;
    }

    // Fetch posts from the database
    postDao postsDao = new postDao();
    List<Map<String, Object>> posts = postsDao.fetchPosts();


%>

<h1>Welcome to the Home Page, <%= userProfile.getName() %></h1>



<h2>Recent Posts:</h2>
<div class="post-container">
    <%
        // Iterate over the list of posts
        for (Map<String, Object> post : posts) {
    %>
    <div class="post">
        <!-- Access post data using keys -->
        <h3><%= post.get("title") %></h3>
        <p><%= post.get("content") %></p>


        <!-- Assuming other keys such as user_id, date, etc. -->
        <!-- You can access them in a similar way -->
    </div>
    <% } %>
</div>

<br/>

<a href="addPost">Add Post</a>

<a href="logout">Logout</a>

</body>
</html>
