package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Requests.UserRequest.UserRequest;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = UserRequest.validate_delete_request(request, response);
        if (userDto == null) {
            response.sendRedirect("error.jsp");
        } else {
            if (this.service.deleteUser(userDto)) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }
}
