package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Proxy.PostsProxy;
import org.example.dpproject.app.Services.UserService;

import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    public void init() {
        this.service = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = UserValidation.validate_login_request(request, response);
        if (userDto == null)
            return;

        QBResults queryResults = this.service.login(userDto);
        System.out.println(Arrays.toString(PostsProxy.getCookies(request)));
        request.getSession().setAttribute("recentPosts", PostsProxy.getCookies(request));
        HelperClass.login(request, response, queryResults);

    }
}
