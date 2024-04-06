package org.example.socialmedia_proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance;
    private static Database database;
    private final Connection connection;

    private DatabaseSingleton() {
        try {
            if (database != null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(database.getUrl(), database.getUsername(), database.getPassword());
            }
            else
                throw new IllegalStateException("Cannot getConnection without specify DB platform first!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
