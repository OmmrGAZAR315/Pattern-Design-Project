<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page import="org.example.socialmedia_proxy.Model.postDao" %>
<%@ page import="org.example.socialmedia_proxy.Model.UserProfile" %>
<%@ page import="org.example.socialmedia_proxy.Model.commentDao" %>
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

    commentDao commentsDao = new commentDao();

%>

<h1>Welcome to the Home Page, <%= userProfile.getName() %></h1>
<a href="addPost">Add Post</a>


<h2>Recent Posts:</h2>
<div class="post-container">
    <%
    for (Map<String, Object> post : posts) {
    %>

    <div class="post-container">
        <div class="post">
            <h3><%= post.get("title") %></h3>
            <p><%= post.get("content") %></p>


            <%
                String postId = String.valueOf(post.get("postId"));
                List<Map<String, Object>> postComments = commentsDao.FetchCommentsForPost(postId);
            %>
            <% if (postComments != null && !postComments.isEmpty()) { %>
            <h4>Comments:</h4>
            <ul>
                <% for (Map<String, Object> comment : postComments) { %>
                <li><%= comment.get("text") %> </li>
                <% } %>
            </ul>

            <% } else { %>
            <p>No comments yet.</p>
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

</body>
</html>
