package org.example.socialmedia_proxy;

public class MS_SQL_SERVER_DB extends Database {
    public MS_SQL_SERVER_DB(String host, String port, String database, String username, String password) {
        super("jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database, username, password);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            setConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
