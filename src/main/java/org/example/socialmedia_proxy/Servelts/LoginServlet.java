package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.socialmedia_proxy.DB.Builder.Builder;
import org.example.socialmedia_proxy.DB.Builder.Query;
import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.PasswordEncryption;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        Query.resetBooleans();
        Query.parameters.clear();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isAuthenticated;
        try {
            isAuthenticated = authenticate(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            response.sendRedirect("home.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Invalid username or password</h3>");
            out.println("</body></html>");
        }
    }

    private boolean authenticate(String username, String password) throws Exception {
        Map<String, Object> result = Builder.query
                .table("users")
                .select("*")
                .where("username", username)
//                .where("password", password)
                .build()
                .first();

        if (result != null) {
            UserProfile user = new UserProfile(result);
            SecretKey key = PasswordEncryption.reconstructKey(user.getKey());
            return Objects.equals(user.getPassword(), PasswordEncryption.encrypt(password, key));
        } else {
            return false;
        }
    }
}