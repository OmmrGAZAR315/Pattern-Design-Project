package org.example.dpproject.DB;

import org.example.dpproject.DB.Helpers.AbsolutePath;
import org.example.dpproject.DB.Helpers.EnvLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.example.dpproject.DB.DatabaseConfig.*;


public abstract class DB {
    private static Connection connection;


    protected static void getDriverManager(String className, String url, String username, String password) {
        int retryAttempts = 3;
        int currentAttempt = 0;
        while (currentAttempt < retryAttempts) {
            try {
                Class.forName(className);
                connection = DriverManager.getConnection("jdbc:" + url, username, password);
                System.out.println("connected");
                break;
            } catch (Exception e) {
                System.err.println("Failed to connect to the database (attempt " + (currentAttempt + 1) + ")");
                currentAttempt++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public abstract void setConnection(String className, String db_connection, String host, String port, String
            database, String username, String password) throws Exception;

    public static void loadDB() {
        try {
            EnvLoader.loadEnv();
            String absolutePath = AbsolutePath.getPath(DB.class);
            // Get the last part of the path, which is the name of the project
            absolutePath = absolutePath.substring(absolutePath.lastIndexOf("/") + 1);
            Class<?> clazz = Class.forName(
                    DB_PACKAGE_PATH.getValue()
                            + DB_PLATFORM_CLASS.getValue()
            );

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
            System.out.println(e.getMessage());
            System.out.println("Error loading DB");
        }
    }

    static {
        loadDB();
    }


}

