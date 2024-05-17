package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dpproject.DB.DB;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.DTOs.PostDto;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Models.Comment;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Proxy.CommentsProxy;
import org.example.dpproject.app.Proxy.PostsProxy;
import org.example.dpproject.app.Services.PostService;
import org.example.dpproject.app.Http.Validation.PostValidation;


@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private PostService service;

    public void init() {
        service = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {
        //optionally do get for a specific post using id
        QBResults queryResults = service.getAllPosts();
        if (queryResults == null || queryResults.getStatusCode() == HttpResponse.INTERNAL_SERVER_ERROR.getCode()) {
            DB.loadDB();
            queryResults = service.getAllPosts();
        }

        Responses postResponse = new Responses();
        if (queryResults.getStatusCode() == HttpResponse.OK.getCode()) {
            QBResults finalQueryResults = queryResults;
            postResponse = new Responses() {
                @Override
                public void anonymousFunctionInSuccessCase() {
                    Post[] posts = HelperClass.convertListMapToArray(Post.class, finalQueryResults.all());
                    HttpSession session = request.getSession();
                    session.setAttribute("posts", posts);
                    PostsProxy.setCookies(resp);
                    CommentsProxy.setCookies(resp);
                }
            };
        }

        postResponse
                .forwardInSuccess("home.jsp")
                .dispatch(request, resp, queryResults, "get all posts", HttpResponse.OK.getCode());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
        PostDto postDto = PostValidation.validate_creatPost_request(request, resp);
        if (postDto == null)
            return;

        QBResults queryResults = service.createPost(postDto);
        Responses postResponse = new Responses() {
            @Override
            public void anonymousFunctionInSuccessCase() {
                new Post().notifySubscribers();
                PostsProxy.setCookies(resp);
            }
        };
        postResponse
                .forwardInSuccess("home.jsp")
                .dispatch(request, resp, queryResults, "create post", HttpResponse.CREATED.getCode());
    }

    static {
        new Post().Observe();
    }
}
