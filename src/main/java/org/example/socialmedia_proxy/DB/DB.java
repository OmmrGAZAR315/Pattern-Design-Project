package org.example.socialmedia_proxy.DB;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import static org.example.socialmedia_proxy.DB.DatabaseConfig.*;

public abstract class DB {
    private static Connection connection;


    protected static void getDriverManager(String className, String url, String username, String password) throws Exception {
        Class.forName(className);
        connection = DriverManager.getConnection("jdbc:" + url, username, password);
    }

    public static Connection getConnection() {
        return connection;
    }

    public abstract void setConnection(String className, String db_connection, String host, String port, String database, String username, String password) throws Exception;

    static {
        System.out.println("DB static block");
        try {
            EnvLoader.loadEnv();

            Class<?> clazz = Class.forName(DB_PLATFORM_CLASS.getValue());
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            Method method = clazz.getMethod(
                    "setConnection",
                    DB_CLASS.getValue().getClass(),
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class
            );
            method.invoke(
                    instance,
                    DB_CLASS.getValue(),
                    DatabaseConfig.DB_CONNECTION.getValue(),
                    DatabaseConfig.HOST.getValue(),
                    DatabaseConfig.PORT.getValue(),
                    DatabaseConfig.DATABASE.getValue(),
                    DatabaseConfig.USERNAME.getValue(),
                    DatabaseConfig.PASSWORD.getValue()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

