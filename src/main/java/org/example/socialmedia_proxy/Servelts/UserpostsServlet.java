package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.Model.UserDataDao;


import org.example.socialmedia_proxy.Model.postDao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@WebServlet ("/userprofile")
public class UserpostsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataDao usr = new UserDataDao();
        postDao dao = new postDao();
        String userId = usr.retrieveUserId(req);
        List<Map<String, Object>> posts = dao.fetchPostsOfUser(userId);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/userprofile.jsp").forward(req, resp);


    }





}
