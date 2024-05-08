package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.Model.Observer.PostObserverImpl;
import org.example.socialmedia_proxy.Model.Observer.postObserve;
import org.example.socialmedia_proxy.Model.Post;
import org.example.socialmedia_proxy.Model.UserDataDao;

import org.example.socialmedia_proxy.Model.postDao;

import java.io.IOException;


@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataDao usr = new UserDataDao();
        postDao pstdao = new postDao();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userId = usr.retrieveUserId(req);
        Post post = new Post(title, content, userId);
        pstdao.savePost(post);
        resp.sendRedirect("home.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("AddPost.jsp");
    }







}
