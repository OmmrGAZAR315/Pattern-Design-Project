package org.example.socialmedia_proxy.DB_CRUD;

import org.example.socialmedia_proxy.Database;
import org.example.socialmedia_proxy.DatabaseSingleton;

import java.sql.Connection;

public class DB {
    public static DatabaseSingleton databaseConnection;

    public DB(Database db) {
        DatabaseSingleton.setDB(db);
        databaseConnection = DatabaseSingleton.getInstance();
    }

    public static Connection getDatabaseConnection() {
        return databaseConnection.getConnection();
    }

}