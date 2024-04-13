package org.example.socialmedia_proxy.Servelts;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.socialmedia_proxy.DB.Builder.Builder;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Builder.query
                .table("users")
                .delete()
//                .where("username", request.getParameter("username"))
//                .where("password", request.getParameter("password"))
                .where("username", "omar1")
                .where("password", "1234")
                .build();
    }
}
