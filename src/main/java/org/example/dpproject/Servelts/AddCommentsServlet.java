package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.UserDataDao;
import org.example.dpproject.app.Models.comment;
import org.example.dpproject.app.Models.commentDao;

import java.io.IOException;

@WebServlet ("/addcomment")
public class AddCommentsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataDao usr = new UserDataDao();
        commentDao com = new commentDao();
        String postid = req.getParameter("postid");
        String content = req.getParameter("content");
        String userId = usr.retrieveUserId(req);
        comment c = new comment(content,userId,postid);
        com.addCommentToDatabase(c);
        resp.sendRedirect("home.jsp");

    }
}
