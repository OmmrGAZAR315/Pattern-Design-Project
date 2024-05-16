package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.DB;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Proxy.PostsProxy;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    public void init() {
        this.service = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = UserValidation.validate_login_request(request, response);
        if (userDto == null)
            return;

        Post[] postsInCookies = PostsProxy.getCookies(request);


        QBResults queryResults = this.service.login(userDto);
        if (queryResults != null && queryResults.getStatusCode() != HttpResponse.INTERNAL_SERVER_ERROR.getCode())
            HelperClass.login(request, response, queryResults);
        else if (postsInCookies != null) {
            DB.loadDB();
            request.getSession().setAttribute("recentPosts", PostsProxy.getCookies(request));
            response.sendRedirect("home.jsp");
        } else {
            System.out.println("No recent posts in cookies.");
            response.sendRedirect("home.jsp");
        }

    }
}
