//package org.example.socialmedia_proxy.DB;
//
//import java.sql.Connection;
//
//public class DatabaseSingleton {
//    private static DB DB;
//    private static volatile DatabaseSingleton instance;
//    private final Connection connection;
//    public static void setDB(DB db) {
//        DB = db;
//    }
//    private DatabaseSingleton() {
//        if (DB != null) {
//            connection = DB.getConnection();
//        } else
//            throw new IllegalStateException("Cannot getConnection without specify DB platform first!");
//    }
//
//    public static DatabaseSingleton getInstance() {
//        if (instance == null) {
//            synchronized (DatabaseSingleton.class) {
//                if (instance == null) {
//                    instance = new DatabaseSingleton();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//}
