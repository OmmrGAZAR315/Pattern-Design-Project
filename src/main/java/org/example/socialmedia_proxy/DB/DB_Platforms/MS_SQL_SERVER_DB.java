package org.example.socialmedia_proxy.DB.DB_Platforms;

import org.example.socialmedia_proxy.DB.DB;

public class MS_SQL_SERVER_DB extends DB {
    @Override
    public void setConnection(String className, String db_connection, String host, String port, String database, String username, String password) throws Exception {
        String url = db_connection + "://" + host + ":" + port + ";databaseName=" + database;

        getDriverManager(className,url, username, password );
    }
}
