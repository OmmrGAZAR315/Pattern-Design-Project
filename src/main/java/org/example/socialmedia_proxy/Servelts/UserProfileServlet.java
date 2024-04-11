package org.example.socialmedia_proxy.Servelts;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder;
import org.example.socialmedia_proxy.Proxy.UserProfileServiceProxy;
import org.example.socialmedia_proxy.UserProfileService;

@WebServlet(name = "UserServlet", value = "/userProfile-servlet")
public class UserProfileServlet extends HttpServlet {
    private UserProfileService userProfileService;

    public void init() throws ServletException {
        super.init();
        userProfileService = new UserProfileServiceProxy();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from request parameter
//        if (request.getParameter("userId")!=null) {
//            int userId = Integer.parseInt(request.getParameter("userId"));
        // Retrieve user profile using the proxy service
//            UserProfile userProfile = userProfileService.getUserProfile(userId);
        // Display user profile


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>User Profile</h2>");
//        out.println("Name: " + userProfile.getName() + "<br>");
//        out.println("Email: " + userProfile.getEmail() + "<br>");
        // Display additional user profile details
        out.println("</body></html>");
        out.close();
//        }
    }

    public void destroy() {
    }
}