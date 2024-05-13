package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Http.Responses.UserResponse.UserResponse;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;
import java.util.Map;

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    protected UserService service;

    @Override
    public void init() {
        service = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = UserValidation.validate_edit_request(request, response);
        if (userDto == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        QBResults queryResult = this.service.updateUser(userDto);

        new UserResponse()
                .dispatch(request, response, queryResult, "edit");


    }
}
