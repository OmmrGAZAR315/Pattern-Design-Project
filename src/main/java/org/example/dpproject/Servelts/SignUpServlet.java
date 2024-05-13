package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Responses.UserResponse.UserResponse;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Proxy.UserProfileService;
import org.example.dpproject.app.Proxy.UserProfileServiceProxy;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;
import java.util.Map;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserProfileService userProfileService;
    private UserService service;

    public void init() throws ServletException {
        this.service = new UserService();
        userProfileService = new UserProfileServiceProxy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = UserValidation.validate_signUp_request(request, response);
        if (userDto == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        QBResults qbResults = this.service.signUp(userDto);
        if (qbResults == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        UserResponse.login(request, response, qbResults);

    }

    public void destroy() {
    }
}