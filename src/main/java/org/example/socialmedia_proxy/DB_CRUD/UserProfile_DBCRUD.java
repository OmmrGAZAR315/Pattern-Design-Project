package org.example.socialmedia_proxy.DB_CRUD;

import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder.UserProfile_DBCRUD_Builder;
import org.example.socialmedia_proxy.DB_CRUD_operation;
import org.example.socialmedia_proxy.Database;
import org.example.socialmedia_proxy.Model.UserProfile;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class UserProfile_DBCRUD extends DB_CRUD_operation {
    private static UserProfile_DBCRUD instance;
    public String query;

    public static void getUserProfile_DBCRUD(Database db) {
        get_DB_CRUD_operation(db);
    }

    public static UserProfile_DBCRUD getUserProfile_DBCRUD() {
        if (instance == null)
            instance = new UserProfile_DBCRUD();

        return instance;
    }

    @Override
    public void create(UserProfile userProfile) {
        {
            String query = "{CALL insert_users(?, ?)}";

            try (CallableStatement callableStatement = databaseConnection.getConnection().prepareCall(query)) {

                callableStatement.setString(1, userProfile.getName());
                callableStatement.setInt(2, userProfile.getAge());
                callableStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void read() {
        query = "SELECT * FROM users ";
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public UserProfile getUserProfile(int userId) {
        return new UserProfile("John", 25, "dsvsdvds@gmauil.com");
    }
}