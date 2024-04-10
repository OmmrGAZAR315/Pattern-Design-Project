package org.example.socialmedia_proxy;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Database {
    private  String url;
    private  String username;
    private  String password;
    private static Connection connection;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        return connection;
    }

    protected static void getDriverManager(String url,String username,String password) throws Exception {
//        System.out.println(
//                "Connecting to database: " + url + " with username: " + username + " and password: " + password
//        );
        connection = DriverManager.getConnection(url, username, password);
    }
}
