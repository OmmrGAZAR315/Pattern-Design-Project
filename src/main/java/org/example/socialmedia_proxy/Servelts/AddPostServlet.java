package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.socialmedia_proxy.DB.QueryBuilder;
import org.example.socialmedia_proxy.Model.Post;
import org.example.socialmedia_proxy.Model.UserProfile;

import java.io.IOException;
import java.util.Map;

@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userId = retrieveUserId(req);
        Post post = new Post(title, content, userId);

        savePost(post);

        resp.sendRedirect("home.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("AddPost.jsp");
    }

    public void savePost(Post post) {

        QueryBuilder query = new QueryBuilder();

        query.table("posts").insert("title",
                        "content", "id")
                .setParameter(post.getTitle())
                .setParameter(post.getContent()).
                setParameter(post.getUserId()).
                build();
    }



    private String retrieveUserId(HttpServletRequest request) {
        QueryBuilder query2 = new QueryBuilder();
        HttpSession session = request.getSession(false); // Retrieve existing session
        if (session != null) {
            UserProfile user = (UserProfile) session.getAttribute("user");
            if (user != null) {
                String username = user.getUsername();
                // Execute query to retrieve user's ID from the database
                Map<String, Object> result = query2.table("users")
                        .select("id")
                        .where("username", username)
                        .build()
                        .first();

                if (result != null && result.containsKey("id")) {
                    // Retrieve user's ID from the result map and convert it to string
                    String userId = String.valueOf(result.get("id"));
                    return userId;
                } else {
                    // Handle case where user is not found in the database
                    // You may want to log this event or return a default value
                }
            } else {
                // Handle case where user object is not found in session
            }
        } else {
            // Handle case where session is not found
        }
        return null; // Return null if user's ID cannot be retrieved
    }

}
