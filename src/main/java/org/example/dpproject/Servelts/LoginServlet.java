package org.example.dpproject.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Model.UserProfile;
import org.example.dpproject.app.Model.PasswordEncryption;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserProfile user;

    public void init() throws ServletException {

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
            session.setAttribute("user", user);
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
        Map<String, Object> result = new QueryBuilder()
                .table("users")
                .select("*")
                .where("username", username)
                .build()
                .first();

        if (result != null) {
            user = new UserProfile(result);
            String id =
                    PasswordEncryption.encrypt(
                            String.valueOf(result.get("id")),
                            PasswordEncryption.reconstructKey(this.user.getKey())
                    );
            user.setId(id);
            SecretKey key = PasswordEncryption.reconstructKey(user.getKey());
            return Objects.equals(user.getPassword(), PasswordEncryption.encrypt(password, key));
        } else {
            return false;
        }
    }
}