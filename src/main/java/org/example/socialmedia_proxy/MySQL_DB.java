package org.example.socialmedia_proxy;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_DB extends Database {
    public MySQL_DB(String host, String port, String database, String username, String password) throws SQLException {
        super("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            setConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
