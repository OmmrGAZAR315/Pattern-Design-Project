package org.example.dpproject.Servelts;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.CookieUtil;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.PostDto;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Services.PostService;
import org.example.dpproject.app.Http.Validation.PostValidation;

import static org.example.dpproject.app.Helpers.CookieUtil.setObjectAsCookie;


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
        Responses postResponse = new Responses();
        if (queryResults.getStatusCode() == HttpResponse.OK.getCode()) {
            postResponse = new Responses() {
                @Override
                public void anonymousFunctionInSuccessCase() {
                    Post[] posts = HelperClass.convertListMapToArray(Post.class, queryResults.all());
                    new Post().Observe();
                    HttpSession session = request.getSession();
                    session.setAttribute("posts", posts);
                }
            };
        }

        postResponse
                .forwardInSuccess("home.jsp")
                .forwardInError("error.jsp")
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
                new Post().Observe();
            }
        };
        postResponse
                .forwardInSuccess("home.jsp")
                .dispatch(request, resp, queryResults, "create post", HttpResponse.CREATED.getCode());

    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        postDao postsDao = new postDao();
//        List<Map<String, Object>> posts = postsDao.fetchPosts();
//
//        UserDao usr = new UserDao();
//        postDao pstDao = new postDao();
//        String title = req.getParameter("title");
//        String content = req.getParameter("content");
//        String userId = usr.retrieveUserId(req);
//        Post post = new Post(title, content, userId);
//        pstDao.savePost(post);
//        Observer code by yours Truly medo
//                PostObserver postwatcher = new PostWatcher(pstDao);
//                pstDao.addObserver(postwatcher);
//                pstDao.notifyObservers();
//
//        resp.sendRedirect("home.jsp");
//    }

}