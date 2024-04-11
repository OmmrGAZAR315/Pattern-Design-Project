package org.example.socialmedia_proxy.DB_Platforms;

import org.example.socialmedia_proxy.Database;

public class MS_SQL_SERVER_DB extends Database {
    public static MS_SQL_SERVER_DB setConnection(String host,String port, String database, String username, String password) {
        String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            getDriverManager(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new MS_SQL_SERVER_DB();
    }
}
