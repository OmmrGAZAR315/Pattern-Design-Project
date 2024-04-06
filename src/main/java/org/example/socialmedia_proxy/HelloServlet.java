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
        String platform = "MySQL"; // or "MS_SQL_SERVER"
        String host = "";
        String port = "";
        String username = "";
        String password = "";
        switch (platform) {
            case "MySQL":
                 host = "127.0.0.1";
                 port = "8088";
                username = "root";
                password = "90950";
                break;
            case "MS_SQL":
                host = "localhost";
                port = "8089";
                break;
        }
        String database = "socialmediaproxydb";

        try {
            Database db = new MySQL_DB(host, port, database, username, password);
            Database db2 = new MS_SQL_SERVER_DB(host, port, database, username, password);
            DatabaseSingleton.setDB(db);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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