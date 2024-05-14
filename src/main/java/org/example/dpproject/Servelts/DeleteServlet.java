package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Http.Validation.UserValidation;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    protected UserService service;

    @Override
    public void init() {
        service = new UserService();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = UserValidation.validate_delete_request(request, response);
        if (userDto == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        QBResults results = this.service.deleteUser(userDto);
        new Responses()
                .forwardInSuccess("home.jsp")
                .forwardInError("error.jsp")
                .dispatch(request, response, results, "delete", HttpResponse.NO_CONTENT.getCode());

    }
}
