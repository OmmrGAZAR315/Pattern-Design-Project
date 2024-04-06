package org.example.socialmedia_proxy;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    DatabaseSingleton databaseConnection;

    public void init() {
        String url = "jdbc:mysql://127.0.0.1:8088/socialmediaproxydb";
        String username = "root";
        String password = "90950";
        Database db = new MySQL_DB(url, username, password);
        DatabaseSingleton.setDB(db);
        databaseConnection = DatabaseSingleton.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sql = "INSERT INTO users (name, age) VALUES (?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "test");
            statement.setInt(2, 30);
            statement.executeUpdate();

            System.out.println("Record inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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