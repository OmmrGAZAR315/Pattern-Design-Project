package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.DAOs.UserDao;


import org.example.dpproject.app.DAOs.postDao;
import org.example.dpproject.app.Observer.PostObserver;
import org.example.dpproject.app.Observer.PostWatcher;

import org.example.dpproject.app.DAOs.postDao;

import java.io.IOException;


@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {



    public void init() throws ServletException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao usr = new UserDao();
        postDao pstdao = new postDao();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userId = usr.retrieveUserId(req);
        Post post = new Post(title, content, userId);
        pstdao.savePost(post);

        //Observer code by yours Truly medo
        PostObserver postwatcher= new PostWatcher(pstdao);
        pstdao.addObserver(postwatcher);
        pstdao.notifyObservers();

        resp.sendRedirect("home.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("AddPost.jsp");
    }


}
