package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Responses.UserResponse.UserResponse;
import org.example.dpproject.app.Http.Validation.UserValidation;
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
        if (userDto == null)
            return;

        QBResults queryResults = this.service.login(userDto);
        if (queryResults == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        UserResponse.login(request, response, queryResults);

    }
}
