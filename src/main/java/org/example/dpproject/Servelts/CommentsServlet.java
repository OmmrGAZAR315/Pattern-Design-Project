package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.DAOs.UserDao;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Models.Comment;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Models.commentDao;
import org.example.dpproject.app.Services.CommentService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {
    private CommentService service;

    @Override
    public void init() {
        service = new CommentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //optionally do get for a specific post using id
        //select all columns from the comments table
        QBResults queryResults = service.getAllComments();
        Responses commentResponse = new Responses();
        if (queryResults.getStatusCode() == HttpResponse.OK.getCode())
            commentResponse = new Responses() {
                @Override
                public void anonymousFunctionInSuccessCase() {
                    Comment[] comments = HelperClass.convertListMapToArray(Comment.class, queryResults.all());
                    HttpSession session = request.getSession();
                    session.setAttribute("post_comments", comments);
                }
            };
        commentResponse
                .forwardInSuccess("home.jsp")
                .forwardInError("error.jsp")
                .dispatch(request, response, queryResults, "get all comments", HttpResponse.OK.getCode());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDao usr = new UserDao();
        commentDao com = new commentDao();
        String postid = req.getParameter("postid");
        String content = req.getParameter("content");
        String userId = usr.retrieveUserId(req);
        Comment c = new Comment(content, userId, postid);
        com.addCommentToDatabase(c);
        resp.sendRedirect("home.jsp");

    }
}
