package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Requests.User.UserRequest;
import org.example.dpproject.app.Requests.User.UserRequest_DELETE;
import org.example.dpproject.app.Services.UserService;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    protected UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        UserRequest_DELETE.validate(request, response);
        this.service.deleteUser((Integer.parseInt(request.getParameter("id"))));
    }
}
