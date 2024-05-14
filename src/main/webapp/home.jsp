<%@ page import="org.example.dpproject.app.Models.User" %>
<%@ page import="org.example.dpproject.app.Models.Post" %>
<%@ page import="org.example.dpproject.app.Models.Comment" %>
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
    <script src="getAllPosts.js"></script>

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

<h1>Welcome to the Home Page, <%= user.getName() %>
</h1>
<a href="add_post.html">Add Post</a>

<h2>Recent Posts:</h2>
<div class="post-container">
        <%
   if(posts == null)
    {%>
    <p>No posts yet.</p>
        <%
    }
   else
   {%>
    <h4>Posts:</h4>
        <%for (Post post : posts) {%>
    <div class="post-container">
        <div class="post">
            <h3><%= post.getTitle() %>
            </h3>
            <p><%= post.getContent() %>
            </p>
            <%
                String postId = String.valueOf(post.getId());
                Comment[] postComments = null;
                Object postCommentsOb = request.getSession().getAttribute("post_comments");
                if (postCommentsOb == null) {%>
            <p>No comments yet.</p>
            <% } else {
                postComments = (Comment[]) postCommentsOb;%>
            <h4>Comments:</h4>
            <ul>
                <% for (Comment comment : postComments) { %>
                <li><%= comment.getText() %>
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

        <% }
   } %>

    <br/>
    <script src="getAllPosts.js"></script>
</body>
</html>
