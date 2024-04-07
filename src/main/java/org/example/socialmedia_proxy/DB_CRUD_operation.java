package org.example.socialmedia_proxy;


import java.sql.*;

public class DB_CRUD_operation {
    DatabaseSingleton databaseConnection;
    DB_CRUD_operation(String platform) {
        String host = "";
        String port = "";
        String username = "";
        String password = "";
        switch (platform) {
            case "MySQL":
                host = "127.0.0.1";
                port = "8088";
                username = "root";
                password = "90950";
                break;
            case "MS_SQL":
                host = "localhost";
                port = "8089";
                break;
        }
        String database = "socialmediaproxydb";
        try {
            Database db = new MySQL_DB(host, port, database, username, password);
//            Database db2 = new MS_SQL_SERVER_DB(host, port, database, username, password);
            DatabaseSingleton.setDB(db);
            databaseConnection = DatabaseSingleton.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(String name, int age) {
        String callSql = "{CALL insert_users(?, ?)}";

        try (CallableStatement callableStatement = databaseConnection.getConnection().prepareCall(callSql)) {

            callableStatement.setString(1, name);
            callableStatement.setInt(2, age);
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void read(String table, String[] columns, String[] values) {
        // Create a new record in the database
    }

    public void update(String table, String[] columns, String[] values) {
        // Create a new record in the database
    }

    public void delete(String table, String[] columns, String[] values) {
        // Create a new record in the database
    }

}
