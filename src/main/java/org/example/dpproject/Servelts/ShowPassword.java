package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.Model.UserProfile;
import org.example.dpproject.PasswordEncryption;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@WebServlet("/showPassword")
public class ShowPassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authenticated") == null)
            response.sendRedirect("login.jsp");
        else {
           UserProfile userProfile = (UserProfile) request.getSession(false).getAttribute("user");
            String password;
            try {
                String keyParam = request.getParameter("key");
            SecretKey secretKey = PasswordEncryption.reconstructKey(keyParam);
                password = PasswordEncryption.decrypt(userProfile.getPassword(), secretKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("shown_password", password);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
