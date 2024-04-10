package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = false;
//        try {
//            isAuthenticated = authenticate(username, password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        if (isAuthenticated) {
            response.sendRedirect("home.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Invalid username or password</h3>");
            out.println("</body></html>");
        }
    }

//    private boolean authenticate(String username, String password) throws SQLException {
//        QueryBuilder.getQueryBuilder().table("users").select(new String[] {"*"})
//                .where("username", "=", username).where("password","=",password);
//        return false;
//        return !QueryBuilder.read().where(
//                " username = '" + username + "' AND password = '" + password + "'"
//        ).build().isEmpty();
//    }
}