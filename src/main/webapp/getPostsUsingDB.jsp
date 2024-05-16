<h4>Posts:</h4>
<%
    for (int i = posts.length - 1; i >= 0; i--) {
        Post post = posts[i];
%>
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
            <%if (user != null) {%>
            <input type="hidden" name="postId" value="<%= post.getId() %>">
            <%
                }
                if (user != null) {
            %>
            <input type="hidden" name="userId" value="<%= user.getId() %>">
            <% } %>
            <label for="comment">Add Comment:</label><br>
            <textarea id="comment" name="text" rows="2" cols="50"></textarea><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>

<% }%>
