package org.example.socialmedia_proxy;


import jakarta.servlet.ServletException;
import org.example.socialmedia_proxy.DB.MySQL_DB;
import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder.UserProfile_DBCRUD_Builder;
import org.example.socialmedia_proxy.Model.UserProfile;

public abstract class DB_CRUD_operation {
    public static DatabaseSingleton databaseConnection;

    protected static void get_DB_CRUD_operation(Database db) {
        DatabaseSingleton.setDB(db);
        databaseConnection = DatabaseSingleton.getInstance();
    }

    public abstract void create(UserProfile userProfile);

    public abstract void read();

    public abstract void update();

    public abstract void delete();

}
