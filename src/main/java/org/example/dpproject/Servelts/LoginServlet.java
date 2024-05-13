package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Responses.UserResponse.UserResponse;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    public void init() {
        this.service = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UserDto userDto = UserValidation.validate_login_request(request, response);
        if (userDto == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        QBResults queryResult = this.service.login(userDto);
        if (queryResult == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        UserResponse userResponse = new UserResponse() {
            public void anonymousFunctionInSuccessCase() {
                HttpSession session = request.getSession();
                session.setAttribute("user", new UserProfile(queryResult.first()));
                session.setAttribute("authenticated", true);
            }
        };
        userResponse
                .forwardInSuccess("home.jsp")
                .forwardInError("error.jsp")
                .dispatch(request, response, queryResult, "login");

    }
}
