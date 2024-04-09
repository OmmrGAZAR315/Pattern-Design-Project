package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB.MySQL_DB;
import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder.UserProfile_DBCRUD_Builder;
import org.example.socialmedia_proxy.DB_CRUD.UserProfile_DBCRUD;
import org.example.socialmedia_proxy.Proxy.UserProfileServiceProxy;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = authenticate(username, password);

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

    private boolean authenticate(String username, String password) {
        System.out.println(UserProfile_DBCRUD_Builder.read().where(
                " username = '" + username + "' AND password = '" + password + "'"
        ).build());
        return !UserProfile_DBCRUD_Builder.read().where(
                " username = '" + username + "' AND password = '" + password + "'"
        ).build().isEmpty();
    }
}