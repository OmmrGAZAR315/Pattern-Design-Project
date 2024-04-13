package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB.Builder.Builder;
import org.example.socialmedia_proxy.DB.Builder.Query;

import java.io.IOException;

@WebServlet("/editUser")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Builder.query
                .table("users")
                .update("username", "password", "name", "age")
                .setParameter(request.getParameter("username"))
                .setParameter(request.getParameter("password"))
                .setParameter(request.getParameter("name"))
                .setParameter(request.getParameter("age"))
                .where("username", request.getParameter("username"))
                .where("password", request.getParameter("password"))
                .build();

        if (Builder.query.first().get("message") == "Success") {
            response.sendRedirect("home.jsp");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<html><body><h3>" + Builder.query.first().get("message") + "</h3></body></html>");
        }
    }
}
