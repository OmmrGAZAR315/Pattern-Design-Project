package org.example.socialmedia_proxy;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Database {
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    public Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

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

    public void setConnection() throws Exception {
        this.connection = DriverManager.getConnection(url, username, password);
    }
}
