package org.example.socialmedia_proxy.DB_CRUD;

import java.sql.*;
import java.util.*;

public class QueryBuilder {
    static String query;
    static boolean isCallParameterSet;
    static boolean isInsertColumnSet;
    static boolean isInsertParameterSet;
    static boolean isWhereSet;
    private static QueryBuilder instance;
    private static String tableName;
    //    static Map<String, String> values = new HashMap<>();
//    static Map<String, String> dataTypes = new HashMap<>();
    static List<Object> parameters = new ArrayList<>();


    public QueryBuilder() {
        isCallParameterSet = false;
        isWhereSet = false;
        instance = this;
    }

    public static QueryBuilder getQueryBuilder() {
        if (instance == null)
            instance = new QueryBuilder();
        return instance;
    }

    public QueryBuilder table(String tableName) {
        QueryBuilder.tableName = tableName;
        return this;
    }

    public QueryBuilder select(String[] columns) {
        query = "SELECT '?' FROM ";
        parameters.add(columns);
        parameters.add(tableName);
        return instance;
    }

    public QueryBuilder insert() {
        query = "INSERT INTO " + tableName;
        query += "( ";
        return instance;
    }

    public static QueryBuilder call(String procedureName) {
        query = "{CALL " + procedureName;
        return instance;
    }

    public QueryBuilder setInsertColumn(Object column) {
        if (!isInsertColumnSet) {
            query += column;
            isInsertColumnSet = true;
        } else
            query += ", " + column + " ";

        return instance;
    }

    public QueryBuilder setInsertColumn() {
        query += ") VALUES ( ";
        return instance;
    }

    public QueryBuilder setInsertParameter(Object parameter) {
        if (!isInsertParameterSet) {
            query += "?";
            isInsertParameterSet = true;
        } else
            query += ", " + "?" + " ";

        parameters.add(parameter);

        return instance;
    }

    public QueryBuilder setInsertParameter() {
        query += ")";
        return instance;
    }

    public static QueryBuilder setCallParameter(String parameter) {
        if (Objects.equals(parameter, "0"))
            query += ")";

        else {
            if (!isCallParameterSet) {
                query += "(" + parameter + " ";
                isCallParameterSet = true;
            } else
                query += ", " + parameter + " ";

            parameters.add(parameter);

        }
        return instance;
    }

    public QueryBuilder where(String column, String operator, Object value) throws SQLException {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";
        query += "?" + "?" + "?";
        parameters.add(column);
        parameters.add(operator);
        parameters.add(value);
        return instance;
    }

    public static QueryBuilder whereIn(String column, String[] values) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";

        query += column + " IN (" + String.join(", ", values) + ")";
        return instance;

    }

    public static QueryBuilder whereNotIn(String column, String[] values) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";

        query += column + " NOT IN (" + String.join(", ", values) + ")";
        return instance;

    }

    public static QueryBuilder whereBetween(String column, String value1, String value2) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";
        query += column + " BETWEEN " + value1 + " AND " + value2;
        return instance;

    }

    public static QueryBuilder whereNotBetween(String column, String value1, String value2) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";
        query += column + " NOT BETWEEN " + value1 + " AND " + value2;
        return instance;

    }

    public static QueryBuilder whereNull(String column) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";
        query += column + " IS NULL";
        return instance;

    }

    public static QueryBuilder whereNotNull(String column) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";
        query += column + " IS NOT NULL";
        return instance;
    }

    public static QueryBuilder whereLike(String column, String value) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";

        query += column + " LIKE " + value;
        return instance;
    }

    public static QueryBuilder whereNotLike(String column, String value) {
        if (!isWhereSet) {
            query += " WHERE ";
            isWhereSet = true;
        } else
            query += " AND ";

        query += column + " NOT LIKE " + value;
        return instance;
    }

//    public static void getMetaData(String tableName, String columnName, String value) throws SQLException {
//
//        DatabaseMetaData metaData = DB_CRUD.getDatabaseConnection().getMetaData();
//        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
//        while (resultSet.next()) {
//            if (Objects.equals(resultSet.getString("COLUMN_NAME"), columnName)) {
//                System.out.println("Column Name: " + resultSet.getString("COLUMN_NAME"));
//                System.out.println("Data Type: " + resultSet.getString("TYPE_NAME"));
//
//                dataTypes.put(resultSet.getString("COLUMN_NAME"), resultSet.getString("TYPE_NAME"));
//                values.put(resultSet.getString("COLUMN_NAME"), value);
//                break;
//            }
//        }
//    }

    public void build() {
        System.out.println(query);
        for (Object parameter : parameters) {
            System.out.println(parameter);
        }
        try (PreparedStatement preparedStatement = DB_CRUD.getDatabaseConnection().prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            resetBooleans();
        }
    }

    public static void resetBooleans() {
        isCallParameterSet = false;
        isInsertColumnSet = false;
        isInsertParameterSet = false;
        isWhereSet = false;
        parameters.clear();
    }

}
