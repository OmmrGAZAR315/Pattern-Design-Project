package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import org.example.socialmedia_proxy.Model.postDao;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
@WebServlet ("/home")
public class ShowPostsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postDao dao = new postDao();

        List<Map<String , Object>> Posts= dao.fetchPosts();
        req.setAttribute("Posts", Posts);
        req.getRequestDispatcher("ShowPosts.jsp").forward(req, resp);

    }
}