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
<a href="add_post.jsp">Add Post</a>

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
                Comment[] postComments = post.comments();
                if (postComments == null) {%>
            <p>No comments yet.</p>
            <% } else {%>
            <h4>Comments:</h4>
            <ul>
                <% for (Comment comment : postComments) { %>
                <li><%= comment.getText() %>
                </li>
                <% } %>
            </ul>
            <% } %>

            <form action="comments" method="post">
                <input type="hidden" name="postId" value="<%= post.getId() %>">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <label for="comment">Add Comment:</label><br>
                <textarea id="comment" name="text" rows="2" cols="50"></textarea><br>
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