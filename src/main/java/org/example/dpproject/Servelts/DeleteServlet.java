package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Http.Requests.User.UserRequest_DELETE;
import org.example.dpproject.app.Model.UserProfile;
import org.example.dpproject.app.Services.UserService;

import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    protected UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserProfile userProfile = UserRequest_DELETE.validate(request, response);

        if (userProfile == null) {
            response.sendRedirect("error.jsp");
        } else {
            if (this.service.deleteUser(userProfile.getId())) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }
}
