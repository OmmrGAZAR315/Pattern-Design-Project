package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.DAOs.UserDao;


import org.example.dpproject.app.DAOs.postDao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@WebServlet ("/userprofile")
public class UserpostsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao usr = new UserDao();
        postDao dao = new postDao();
        String userId = usr.retrieveUserId(req);
        List<Map<String, Object>> posts = dao.fetchPostsOfUser(userId);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/userprofile.jsp").forward(req, resp);


    }





}
