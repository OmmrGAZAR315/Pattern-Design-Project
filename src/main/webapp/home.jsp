<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page import="org.example.dpproject.app.DAOs.postDao" %>

<%@ page import="org.example.dpproject.app.Models.commentDao" %>
<%@ page import="org.example.dpproject.app.Models.UserProfile" %>
<%@ page import="org.example.dpproject.DB.QBResults" %>
<%@ page import="org.example.dpproject.app.Helpers.HttpResponse" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="protectPage.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/normalization.css">
    <!-- Main template CSS file -->
    <link rel="stylesheet" href="CSS/main.css">
    <link rel="stylesheet" href="CSS/Home.css">
</head>
<body>
    <header class="home-header">
        <ul><li><a href="userprofile">UserProfile</a></li></ul>
        <h1 class="home-h1">Forum</h1>
<%--idea: make the userprofile of header is simply the name of the user--%>
<%--idea: remove toggle add post and make it simply static in home--%>
    </header>
    <main>
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
    Collections.reverse(posts);
    commentDao commentsDao = new commentDao();

%>
<%--    <h1>Welcome to the Home Page, <%= userProfile.getName() %></h1>--%>
<%--    <button id="showFormButton">Add Post</button>--%>

    <div id="postForm" class="add-post">
        <h1>Create post</h1>
        <form action="addPost" method="post">
            <input placeholder="title" type="text" id="title" name="title">
            <textarea placeholder="Body" id="content" name="content"></textarea>
            <button type="submit" class="btn">Post</button>
        </form>
    </div>

    <div class="posts-container">
        <%
    for (Map<String, Object> post : posts) {
    %>

    <div class="post-container">
        <div class="post">
            <h3><%= post.get("title") %>
            </h3>
            <p><%= post.get("content") %>
            </p>


            <%
                String postId = String.valueOf(post.get("postId"));
                QBResults postComments = commentsDao.FetchCommentsForPost(postId);
            %>
            <% if (postComments.getStatusCode() == HttpResponse.NOT_FOUND.getCode()) { %>
            <p>No comments yet.</p>
            <% } else { %>
            <h4>Comments:</h4>
            <ul>
                <% for (Map<String, Object> comment : postComments.all()) { %>
                <li><%= comment.get("text") %>
                </li>
                <% } %>
            </ul>
            <% } %>

            <!-- Add a form to allow users to add comments -->
            <form action="addcomment" method="post">
                <input type="hidden" name="postid" value="<%= postId %>">
                <input placeholder="Write a comment.." id="comment" name="content" rows="2" cols="50"></input>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>

        <% } %>

    <br/>


    <a href="logout">Logout</a>
</main>
            <script src="AddPostToggle.js" ></script>
</body>
</html>
