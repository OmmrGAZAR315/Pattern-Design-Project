package org.example.dpproject.Servelts;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.dpproject.app.Proxy.Cacheable;
import org.example.dpproject.app.Proxy.UserProfileService;
import org.example.dpproject.app.Proxy.UserProfileServiceProxy;

@WebServlet("/userProfile")
public class UserProfileServlet extends HttpServlet {
    private UserProfileService userProfileService;
    private Cacheable cacheable;

    @Override
    public void init() throws ServletException {
        super.init();
        userProfileService = new UserProfileServiceProxy();
        cacheable = (Cacheable) userProfileService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (userProfileService.getUserProfile(userId) == null) {
            request.getSession().setAttribute("error", "User not found");
            response.sendRedirect("user_ids.jsp");
        }
        else{
            request.getSession().setAttribute("users", cacheable.getUserProfileCache());
            response.sendRedirect("view users.jsp");
        }
    }
}
