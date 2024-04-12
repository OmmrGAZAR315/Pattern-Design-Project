package org.example.socialmedia_proxy.DB;

import org.example.socialmedia_proxy.DB.Builder.Builder;
import org.example.socialmedia_proxy.DB.Builder.Query;

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
        if (columns.length == 1) {
            if (columns[0].equals("*"))
                Query.selectAll = true;

            Query.selectedColumns = columns[0];
        } else {
            Query.selectedColumns = String.join(",", columns);
        }
        Query.query = "SELECT " + Query.selectedColumns + " FROM " + Query.tableName;
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

    public QueryBuilder setInsertColumn(String column) {
        if (Query.isInsertColumnSet)
            Query.query += ", " + column + " ";
        else {
            Query.query += column;
            Query.isInsertColumnSet = true;
        }
        return this;
    }

    public QueryBuilder closeInsertColumn() {
        Query.query += ") VALUES ( ";
        return this;
    }

    public QueryBuilder setInsertParameter(Object parameter) {
        if (Query.isInsertParameterSet)
            Query.query += ", " + "?" + " ";
        else {
            Query.query += "?";
            Query.isInsertParameterSet = true;
        }

        Query.parameters.add(parameter);
        return this;
    }

    public QueryBuilder closeInsertParameter() {
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

    public Builder build() {
        System.out.println(Query.query);
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DB.getConnection().prepareStatement(Query.query, Statement.RETURN_GENERATED_KEYS)) {
            if (!Query.parameters.isEmpty()) {
                for (int i = 0; i < Query.parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, Query.parameters.get(i));
                }
            }
            Query.parameters.clear();
            switch (Query.queryType) {
                case READ:
                    resultSet = preparedStatement.executeQuery();
                    List<Map<String, Object>> fetchedAllRows = new ArrayList<>();
                    while (resultSet.next()) {
                        Map<String, Object> row = new HashMap<>();
                        if (Query.selectAll)
                            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                                row.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        else
                            for (String column : Query.selectedColumns.split(","))
                                row.put(column, resultSet.getObject(column));
                        fetchedAllRows.add(row);
                    }
                    Query.importedData.put("results", fetchedAllRows);

                    break;
                case CUD:
                    preparedStatement.executeUpdate();
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next())
                        Query.importedData.put("results",
                                Collections.singletonList(
                                        Map.of("id", resultSet.getInt(1)
                                        )
                                )
                        );

                    break;
            }
            return this;
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

    @Override
    public Map<String, Object> first() {
        if (Query.importedData.get("results").isEmpty())
            return null;
        return Query.importedData.get("results").get(0);
    }

    @Override
    public Map<String, Object> last() {
        if (Query.importedData.get("results").isEmpty())
            return null;
        return Query.importedData.get("results").get(Query.importedData.get("results").size() - 1);
    }

    @Override
    public List<Map<String, Object>> all() {
        if (Query.importedData.get("results").isEmpty())
            return null;
        return Query.importedData.get("results");
    }

}
