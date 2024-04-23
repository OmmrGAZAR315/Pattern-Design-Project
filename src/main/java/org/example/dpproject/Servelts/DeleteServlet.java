package org.example.dpproject.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QueryBuilder;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        new QueryBuilder()
                .table("users")
                .delete()
                .where("username", request.getParameter("username"))
                .where("password", request.getParameter("password"))
                .build();
    }
}
