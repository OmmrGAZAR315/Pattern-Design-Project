package org.example.socialmedia_proxy;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private DB_CRUD_operation crudOperation;

    public void init() {
     crudOperation =new DB_CRUD_operation("MySQL");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         crudOperation.create("name", 20);
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}