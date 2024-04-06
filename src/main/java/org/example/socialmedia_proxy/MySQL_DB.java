package org.example.socialmedia_proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_DB extends Database {
    public MySQL_DB(String url, String username, String password) {
        super(url, username, password);
    }
}
