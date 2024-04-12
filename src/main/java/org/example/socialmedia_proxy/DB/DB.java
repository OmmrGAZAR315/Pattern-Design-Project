package org.example.socialmedia_proxy.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DB {
    private static Connection connection;

    protected static void getDriverManager(String className, String url, String username, String password) throws Exception {
        connection = DriverManager.getConnection("jdbc:" + url, username, password);
    }

    public static Connection getConnection() {
        return connection;
    }

    public abstract void setConnection(String db_connection, String host, String port, String database, String username, String password, String className) throws Exception;
}

