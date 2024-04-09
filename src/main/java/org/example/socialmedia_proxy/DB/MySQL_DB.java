package org.example.socialmedia_proxy.DB;

import jakarta.servlet.ServletException;
import org.example.socialmedia_proxy.Database;

public class MySQL_DB extends Database {
    public static MySQL_DB setConnection(String host,String port,String database, String username, String password) throws ServletException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            getDriverManager(url, username, password);

        } catch (Exception e) {
            throw new ServletException("MySQL Server not found!");
        }
        return new MySQL_DB();
    }
}
