package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB_CRUD.Builder.Builder;
import org.example.socialmedia_proxy.DB_CRUD.Builder.Query;
import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder;
import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.Proxy.UserProfileServiceProxy;
import org.example.socialmedia_proxy.UserProfileService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signupServlet")
public class SignUpServlet extends HttpServlet {
    private UserProfileService userProfileService;
    public void init() throws ServletException {
        super.init();
        userProfileService = new UserProfileServiceProxy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from request parameter
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserProfile userProfile = new UserProfile(username, password);
System.out.println("username: "+userProfile.getUsername());
System.out.println("password: "+userProfile.getPassword());
        Builder.query
                .table("users")
                .insert()
                .setInsertColumn("username")
                .setInsertColumn("password")
                .setInsertColumn()
                .setInsertParameter(userProfile.getUsername())
                .setInsertParameter(userProfile.getPassword())
                .setInsertParameter()
                .build();
        System.out.println("User Profile Created");
        response.sendRedirect("home.jsp");
    }

    public void destroy() {
    }
}