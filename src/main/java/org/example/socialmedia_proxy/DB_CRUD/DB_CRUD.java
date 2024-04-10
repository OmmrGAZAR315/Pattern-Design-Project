package org.example.socialmedia_proxy.DB_CRUD;

import org.example.socialmedia_proxy.Database;
import org.example.socialmedia_proxy.DatabaseSingleton;
import org.example.socialmedia_proxy.EnvLoader;
import org.example.socialmedia_proxy.Model.UserProfile;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DB_CRUD {
    public static DatabaseSingleton databaseConnection;
    public static QueryBuilder query;

    private DB_CRUD() {
    }

    public DB_CRUD(Database db) {
        DatabaseSingleton.setDB(db);
        databaseConnection = DatabaseSingleton.getInstance();
        query = new QueryBuilder();
    }

//    public static void create(UserProfile userProfile) {
//        {
//            try (CallableStatement callableStatement =
//                         databaseConnection.getConnection().prepareCall(QueryBuilder.getQuery())) {
//                callableStatement.setString(1, userProfile.getName());
//                callableStatement.setInt(2, userProfile.getAge());
//                callableStatement.execute();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//    }

    public static Connection getDatabaseConnection() {
        return databaseConnection.getConnection();
    }

//    public static void read() {
//        query = QueryBuilder.table("users").select(new String[] {"*"});
//    }

    public static void update() {

    }

    public static void delete() {

    }

    public static UserProfile getUserProfile(int userId) {
        return new UserProfile("John", 25, "dsvsdvds@gmauil.com");
    }
}