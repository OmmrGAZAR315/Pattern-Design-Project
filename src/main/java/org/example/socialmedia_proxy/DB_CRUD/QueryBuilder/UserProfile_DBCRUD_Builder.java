package org.example.socialmedia_proxy.DB_CRUD.QueryBuilder;

import org.example.socialmedia_proxy.DB_CRUD.UserProfile_DBCRUD;

import java.sql.*;
import java.util.*;

import static org.example.socialmedia_proxy.DB_CRUD_operation.databaseConnection;

public class UserProfile_DBCRUD_Builder{
    public static final UserProfile_DBCRUD userProfile_dBCRUD=UserProfile_DBCRUD.getUserProfile_DBCRUD();
    Connection connection = databaseConnection.getConnection();
    public UserProfile_DBCRUD_Builder() {
    }
    public static UserProfile_DBCRUD_Builder read(){
        userProfile_dBCRUD.read();
        return new UserProfile_DBCRUD_Builder();
    }
    public UserProfile_DBCRUD_Builder where(String condition) {
        userProfile_dBCRUD.query += "WHERE "+condition;
        return this;
    }
    public Map<String, String> build() {
        Map<String, String> values = new HashMap<>();

        try ( CallableStatement callableStatement = connection.prepareCall(userProfile_dBCRUD.query)) {

            // If the query returns a result set
            if (callableStatement.execute()) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    while (resultSet.next()) {
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = metaData.getColumnName(i);
                            String value = resultSet.getString(columnName);
                            values.put(columnName, value);
                        }

                        // Process the values map containing all column names and corresponding values
                        // For example, you can add it to a list or perform any other operations
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
        String[] desiredOrder = {"id", "name", "age", "username", "password"};

        // Reorder the columns

        return reorderColumns(values, desiredOrder);
    }
    private static Map<String, String> reorderColumns(Map<String, String> originalMap, String[] desiredOrder) {
        Map<String, String> reorderedMap = new LinkedHashMap<>();
        for (String key : desiredOrder) {
            if (originalMap.containsKey(key)) {
                reorderedMap.put(key, originalMap.get(key));
            }
        }
        return reorderedMap;
    }
}
