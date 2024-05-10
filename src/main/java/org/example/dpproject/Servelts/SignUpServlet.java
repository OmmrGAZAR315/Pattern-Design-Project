package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Http.Responses.HttpResponse;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Proxy.UserProfileService;
import org.example.dpproject.app.Proxy.UserProfileServiceProxy;

import java.io.IOException;
import java.util.List;
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
        Builder result = new QueryBuilder()
                .table("users")
                .insert("username", "password", "name", "age", "secretKey")
                .setParameter(userProfile.getUsername())
                .setParameter(userProfile.getPassword())
                .setParameter(userProfile.getName())
                .setParameter(userProfile.getAge())
                .setParameter(userProfile.getKey())
                .build();
        if ((int) result.getMessages().get("status_code") == HttpResponse.CREATED.getCode()) {
        }
        Map<String, Object> objectMap = result.first();
        int userId = (int) objectMap.get("id");
        objectMap = new QueryBuilder()
                .table("users")
                .select("*")
                .where("id", userId)
                .build()
                .first();
        userProfile = new UserProfile(objectMap);
        userProfile.setId(userId);
        request.getSession().setAttribute("user", userProfile);
        request.getSession().setAttribute("authenticated", true);
        response.sendRedirect("home.jsp");
    }

    public void destroy() {
    }
}