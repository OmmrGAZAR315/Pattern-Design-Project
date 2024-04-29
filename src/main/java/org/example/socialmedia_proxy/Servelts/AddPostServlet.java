package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.socialmedia_proxy.DB.QueryBuilder;
import org.example.socialmedia_proxy.Model.Post;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userId = retrieveUserId(req);

        Post post = new Post(title, content,userId);
        savePost(post);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("AddPost.jsp");
    }

    public void savePost(Post post) {
        QueryBuilder query = new QueryBuilder();

        query.table("posts").insert("title",
                "content")
                .setParameter(post.getTitle())
                .setParameter(post.getContent()).
                setParameter(post.getUserId()).
                build();



    }

    private String retrieveUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieves the session without creating a new one if it doesn't exist
        if (session != null) {
            Object userIdObject = session.getAttribute("id");
            if (userIdObject != null && userIdObject instanceof Integer) {
                return (String) userIdObject; // Casting the user ID to an integer and returning it
            }
        }
        // If user ID is not found in the session or is not an integer, return a default value or handle the situation as needed
        return "Not Found"; // Placeholder value or handle the situation as needed
    }

}
