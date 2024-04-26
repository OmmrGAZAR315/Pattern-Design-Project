package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.Model.UserProfile;
import org.example.dpproject.PasswordEncryption;
import org.example.dpproject.Proxy.UserProfileService;
import org.example.dpproject.Proxy.UserProfileServiceProxy;

import java.io.IOException;
import java.util.Map;


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
        Map<String, Object> query = new QueryBuilder()
                .table("users")
                .insert("username", "password", "name", "age", "secretKey")
                .setParameter(userProfile.getUsername())
                .setParameter(userProfile.getPassword())
                .setParameter(userProfile.getName())
                .setParameter(userProfile.getAge())
                .setParameter(userProfile.getKey())
                .build()
                .first();
        int userId = (int) query.get("id");
        query = new QueryBuilder()
                .table("users")
                .select("*")
                .where("id", userId)
                .build()
                .first();
        userProfile = new UserProfile(query);
        try {
            String id = PasswordEncryption.encrypt(
                    String.valueOf(userId),
                    PasswordEncryption.reconstructKey(userProfile.getKey())
            );
            userProfile.setId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("user", userProfile);
        request.getSession().setAttribute("authenticated", true);
        response.sendRedirect("home.jsp");
    }

    public void destroy() {
    }
}