package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Models.User;
import org.example.dpproject.app.Models.PasswordEncryption;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Map;

@WebServlet("/showPassword")
public class ShowPassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authenticated") == null)
            response.sendRedirect("login.jsp");
        else {
            System.out.println("ShowPassword");
            String id = request.getParameter("id");
            String keyParam = request.getParameter("key");
            SecretKey secretKey = PasswordEncryption.reconstructKey(keyParam);
            int userId;
            try {
                userId = Integer.parseInt(PasswordEncryption.decrypt(id, secretKey));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Map<String, Object> user = new QueryBuilder()
                    .select("*")
                    .where("id", userId)
                    .build()
                    .first();

            User userProfile = new User(user);
            System.out.println(userProfile);
            String password;
            try {
                password = PasswordEncryption.decrypt(userProfile.getPassword(), secretKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("shown_password", password);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
