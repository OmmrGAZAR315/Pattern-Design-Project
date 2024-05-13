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
    Collections.reverse(posts);
    commentDao commentsDao = new commentDao();

%>

<h1>Welcome to the Home Page, <%= userProfile.getName() %>
</h1>


<button id="showFormButton">Add Post</button>

<div id="postForm" style="display: none;">
    <h1>Add Post</h1>
    <form action="addPost" method="post">
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title"><br>

        <label for="content">Content:</label><br>
        <textarea id="content" name="content"></textarea><br>

        <button type="submit">Submit</button>
    </form>
</div>




<h2>Recent Posts:</h2>
<div class="post-container">
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
                <label for="comment">Add Comment:</label><br>
                <textarea id="comment" name="content" rows="2" cols="50"></textarea><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>

        <% } %>

    <br/>


    <a href="logout">Logout</a>
            <!-- Link to external JavaScript file -->
            <script src="AddPostToggle.js" ></script>
</body>
</html>
