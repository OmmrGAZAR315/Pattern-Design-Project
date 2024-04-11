package org.example.socialmedia_proxy.DB_CRUD;

import org.example.socialmedia_proxy.DB_CRUD.Builder.Builder;
import org.example.socialmedia_proxy.DB_CRUD.Builder.Query;
import org.example.socialmedia_proxy.QueryType;

import java.sql.*;
import java.util.*;

public class QueryBuilder implements Builder {
    private static QueryBuilder instance;

    public static QueryBuilder getQueryBuilder() {
        if (instance == null)
            instance = new QueryBuilder();
        return instance;
    }

    public QueryBuilder() {
        Query.isCallParameterSet = false;
        Query.isWhereSet = false;
        instance = this;
    }

    public QueryBuilder table(String tableName) {
        Query.tableName = "`" + tableName + "`";
        Query.resetBooleans();
        Query.parameters.clear();
        return this;
    }

    public QueryBuilder select(String... columns) {
        String selectColumns;
        if (columns.length == 1) {
            selectColumns = columns[0];
        } else {
            selectColumns = String.join(",", columns);
        }
        Query.query = "SELECT " + selectColumns + " FROM " + Query.tableName;
        Query.queryType = QueryType.READ;
        return this;
    }

    public QueryBuilder insert() {
        Query.query = "INSERT INTO " + Query.tableName;
        Query.query += "( ";
        Query.queryType = QueryType.CUD;
        return this;
    }

    public QueryBuilder call(String procedureName) {
        Query.query = "{CALL " + procedureName;
        return this;
    }

    public QueryBuilder setSelect(Object column) {
        if (!Query.isInsertColumnSet) {
            Query.query += column;
            Query.isInsertColumnSet = true;
        } else
            Query.query += ", " + column + " ";

        return this;
    }

    public QueryBuilder setInsertColumn(Object column) {
        if (!Query.isInsertColumnSet) {
            Query.query += column;
            Query.isInsertColumnSet = true;
        } else
            Query.query += ", " + column + " ";

        return this;
    }

    public QueryBuilder setInsertColumn() {
        Query.query += ") VALUES ( ";
        return this;
    }

    public QueryBuilder setInsertParameter(Object parameter) {
        if (!Query.isInsertParameterSet) {
            Query.query += "?";
            Query.isInsertParameterSet = true;
        } else
            Query.query += ", " + "?" + " ";

        Query.parameters.add(parameter);
        return this;
    }

    public QueryBuilder setInsertParameter() {
        Query.query += ")";
        return this;
    }

    public QueryBuilder setCallParameter(String parameter) {
        if (Objects.equals(parameter, "0"))
            Query.query += ")";

        else {
            if (!Query.isCallParameterSet) {
                Query.query += "(" + parameter + " ";
                Query.isCallParameterSet = true;
            } else
                Query.query += ", " + parameter + " ";

            Query.parameters.add(parameter);

        }
        return this;
    }

    public QueryBuilder where(String column, Object value) {
        return where(column, "=", value);
    }

    public QueryBuilder where(String column, String operator, Object value) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";

        Query.query += column + operator + " ? ";
//        parameters.add(column);
//        parameters.add(operator);
        Query.parameters.add(value);
        return this;
    }


    public QueryBuilder whereIn(String column, String[] values) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";

        Query.query += column + " IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereNotIn(String column, String[] values) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";

        Query.query += column + " NOT IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereBetween(String column, String value1, String value2) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";
        Query.query += column + " BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNotBetween(String column, String value1, String value2) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";
        Query.query += column + " NOT BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNull(String column) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";
        Query.query += column + " IS NULL";
        return this;

    }

    public QueryBuilder whereNotNull(String column) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";
        Query.query += column + " IS NOT NULL";
        return this;
    }

    public QueryBuilder whereLike(String column, String value) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";

        Query.query += column + " LIKE " + value;
        return this;
    }

    public QueryBuilder whereNotLike(String column, String value) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " AND ";

        Query.query += column + " NOT LIKE " + value;
        return this;
    }

//    public  void getMetaData(String tableName, String columnName, String value) throws SQLException {
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
        System.out.println(Query.query);
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DB.getDatabaseConnection().prepareStatement(Query.query, Statement.RETURN_GENERATED_KEYS)) {
            if (!Query.parameters.isEmpty()) {
                for (int i = 0; i < Query.parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, Query.parameters.get(i));
                }
            }
            Query.parameters.clear();
            switch (Query.queryType) {
                case READ:
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("id"));
                        Query.importedData.put(
                                "RResults_" + resultSet.getString("id"),
                                new Object[]{
                                        resultSet.getString("username"),
                                        resultSet.getString("password")
                                });

                        Query.parameters.add(resultSet.getString("id"));
                    }
                    break;
                case CUD:
                    preparedStatement.executeUpdate();
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        Query.parameters.add(resultSet.getLong(1));
                    }
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Query.resetBooleans();
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
