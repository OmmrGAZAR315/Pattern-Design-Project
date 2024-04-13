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
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        UserProfile userProfile = new UserProfile(username, password,name, age);
        Builder.query
                .table("users")
                .insert("username","password","name","age")
                .setInsertParameter(userProfile.getUsername())
                .setInsertParameter(userProfile.getPassword())
                .setInsertParameter(userProfile.getName())
                .setInsertParameter(userProfile.getAge())
                .build();
        response.sendRedirect("index.jsp");
    }

    public void destroy() {
    }
}