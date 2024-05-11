package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Requests.UserRequest.UserRequest;
import org.example.dpproject.app.Http.Responses.UserResponse.UserResponse;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;
import java.util.Map;

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
            Map<String, Object> queryResult = this.service.deleteUser(userDto);
            UserResponse.dispatch(request, response, queryResult, "delete");
        }
    }
}
