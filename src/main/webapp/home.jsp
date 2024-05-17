<%@ page import="org.example.dpproject.app.Models.User" %>
<%@ page import="org.example.dpproject.app.Models.Post" %>
<%@ page import="org.example.dpproject.app.Models.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<jsp:include page="protectPage.jsp"/>--%>
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
<a href="logout">Logout</a>
<%
    Object userOb = request.getSession().getAttribute("user");
    Object postsOb = request.getSession().getAttribute("posts");
    User user = null;
    Post[] posts = null;
    if (userOb != null)
        user = (User) userOb;

    if (postsOb != null)
        posts = (Post[]) postsOb;

%>

<h1>Welcome to the Home Page,
    <% if (user != null) { %>
    <%= user.getName() %>
    <% } %>
</h1>
<a href="add_post.jsp">Add Post</a>

<h2>Recent Posts:</h2>
<div class="post-container">
        <%
   if(posts == null){
if(request.getSession().getAttribute("recentPosts") != null){%>
    <%@ include file="getPostsUsingCookies.jsp" %>
        <%} else {%>
    <p>No posts yet.</p>
        <%}
    }  else {%>
    <%@ include file="getPostsUsingDB.jsp" %>

        <%  } %>

    <br/>
    <script src="getAllPosts.js"></script>

</body>
</html>