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
import java.util.Arrays;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserProfileService userProfileService;

    public void init() throws ServletException {
        super.init();
        userProfileService = new UserProfileServiceProxy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        UserProfile userProfile;
        try {
            userProfile = new UserProfile(username, password, name, age);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Builder.query
                .table("users")
                .insert("username", "password", "name", "age", "secretKey")
                .setParameter(userProfile.getUsername())
                .setParameter(userProfile.getPassword())
                .setParameter(userProfile.getName())
                .setParameter(userProfile.getAge())
                .setParameter(userProfile.getKey())
                .build();
        request.getSession().setAttribute("authenticated", true);
        response.sendRedirect("home.jsp");
    }

    public void destroy() {
    }
}