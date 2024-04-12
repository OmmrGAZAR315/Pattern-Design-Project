package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB.Builder.Builder;
import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.Proxy.UserProfileServiceProxy;
import org.example.socialmedia_proxy.Proxy.UserProfileService;

import java.io.IOException;

@WebServlet("/signupServlet")
public class SignUpServlet extends HttpServlet {
    private UserProfileService userProfileService;

    public void init() throws ServletException {
        super.init();
        userProfileService = new UserProfileServiceProxy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserProfile userProfile = new UserProfile(username, password, 0);
        System.out.println("username: " + userProfile.getUsername());
        System.out.println("password: " + userProfile.getPassword());
        Builder.query
                .table("users")
                .insert()
                .setInsertColumn("username")
                .setInsertColumn("password")
                .closeInsertColumn()
                .setInsertParameter(userProfile.getUsername())
                .setInsertParameter(userProfile.getPassword())
                .closeInsertParameter()
                .build();
        response.sendRedirect("index.jsp");
    }

    public void destroy() {
    }
}