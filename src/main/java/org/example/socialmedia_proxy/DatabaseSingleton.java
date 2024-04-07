package org.example.socialmedia_proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance;
    private static Database database;
    private final Connection connection;

    private DatabaseSingleton() {
        if (database != null) {
            connection = database.getConnection();
        } else
            throw new IllegalStateException("Cannot getConnection without specify DB platform first!");
    }

    public static void setDB(Database db) {
        database = db;
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            synchronized (DatabaseSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
