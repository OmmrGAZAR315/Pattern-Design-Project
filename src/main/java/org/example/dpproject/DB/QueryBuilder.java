package org.example.dpproject.DB;


import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.Builder.Query;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.sql.*;
import java.util.*;

public class QueryBuilder implements Builder {
    Query query;

    public QueryBuilder() {
        query = new Query();
    }

    public QueryBuilder table(String tableName) {
        query.tableName = "`" + tableName + "`";
        query.columns.clear();
        query.parameters.clear();
        return this;
    }

    public QueryBuilder select(String... columns) {
        String columnsSplitByComma = "id, ";
        if (columns.length == 1) {
            if (columns[0].equals("*"))
                query.selectAll = true;

            columnsSplitByComma = columns[0];
        } else {
            columnsSplitByComma += String.join(", ", columns);
        }
        query.query = "SELECT " + columnsSplitByComma + " FROM " + query.tableName;
        query.queryType = QueryType.READ;
        query.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public QueryBuilder insert(String... columns) {
        query.query = "INSERT INTO " + query.tableName;
        query.query += "( ";
        query.query += String.join(",", columns);
        query.query += ") VALUES ( ";

        query.parameters.add(columns.length);
        query.queryType = QueryType.Create;
        return this;
    }

    public QueryBuilder update(String... columns) {
        query.query = "UPDATE " + query.tableName;
        query.query += " SET ";

        query.columns.addAll(Arrays.asList(columns));
        query.queryType = QueryType.Update;
        return this;
    }

    public QueryBuilder delete() {
        query.query = "DELETE FROM " + query.tableName;
        query.queryType = QueryType.CUD;
        return this;
    }

    public QueryBuilder call(String procedureName) {
        query.query = "{CALL " + procedureName;
        return this;
    }

    public QueryBuilder setParameter(Object parameter) {
        if (query.queryType == QueryType.Create)
            setInsertParameter(parameter);

        else if (query.queryType == QueryType.Update)
            setUpdateParameter(parameter);

        return this;
    }

    public void setInsertParameter(Object parameter) {
        if (query.isParameterSet)
            query.query += ", " + "?" + " ";
        else {
            query.query += "?";
            query.isParameterSet = true;
        }

        query.parameters.add(parameter);
        int numberOfColumns = (int) query.parameters.get(0);
        // if the number of columns is equal to the number of parameters
        if (query.parameters.size() - 1 == numberOfColumns) {
            query.query += ")";
            query.parameters.remove(0);
        }
    }

    public void setUpdateParameter(Object parameter) {
        query.query += query.columns.get(0) + " = ?";
        query.columns.remove(0);
        query.parameters.add(parameter);

        if (!query.columns.isEmpty())
            query.query += ", ";

    }

    public QueryBuilder setCallParameter(String parameter) {
        if (Objects.equals(parameter, "0"))
            query.query += ")";

        else {
            if (!query.isCallParameterSet) {
                query.query += "(" + parameter + " ";
                query.isCallParameterSet = true;
            } else
                query.query += ", " + parameter + " ";

            query.parameters.add(parameter);

        }
        return this;
    }

    public QueryBuilder where(String column, Object value) {
        return where(column, "=", value, "AND");
    }

    public QueryBuilder orWhere(String column, Object value) {
        return where(column, "=", value, "OR");
    }

    public QueryBuilder whereId(Object value) {
        return where("id", "=", value, "AND");
    }

    public QueryBuilder where(String column, String operator, Object value, String logicalOperator) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " " + logicalOperator + " ";

        query.query += column + operator + " ? ";
        query.parameters.add(value);
        return this;
    }

    public QueryBuilder whereIn(String column, String[] values) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";

        query.query += column + " IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereNotIn(String column, String[] values) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";

        query.query += column + " NOT IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereBetween(String column, String value1, String value2) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";
        query.query += column + " BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNotBetween(String column, String value1, String value2) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";
        query.query += column + " NOT BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNull(String column) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";
        query.query += column + " IS NULL";
        return this;

    }

    public QueryBuilder whereNotNull(String column) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";
        query.query += column + " IS NOT NULL";
        return this;
    }

    public QueryBuilder whereLike(String column, String value) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";

        query.query += column + " LIKE " + value;
        return this;
    }

    public QueryBuilder whereNotLike(String column, String value) {
        if (!query.isWhereSet) {
            query.query += " WHERE ";
            query.isWhereSet = true;
        } else
            query.query += " AND ";

        query.query += column + " NOT LIKE " + value;
        return this;
    }


    public QBResults build() {
        System.out.println(query.query);
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DB.getConnection().prepareStatement(query.query, Statement.RETURN_GENERATED_KEYS)) {
            if (!query.parameters.isEmpty()) {
                for (int i = 0; i < query.parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, query.parameters.get(i));
                }
            }
            HttpResponse response;
            query.parameters.clear();
            switch (query.queryType) {
                case READ:
                    try {
                        resultSet = preparedStatement.executeQuery();
                        response = HttpResponse.OK;
                    } catch (Exception e) {
                        response = HttpResponse.BAD_REQUEST;
                        query.importedData.put("messages", addMessage(response));
                        return new QBResults(query.importedData);
                    }
                    List<Map<String, Object>> fetchedAllRows = new ArrayList<>();
                    if (!resultSet.isBeforeFirst())
                        response = HttpResponse.NOT_FOUND;
                    else {
                        while (resultSet.next()) {
                            Map<String, Object> row = new HashMap<>();
                            if (query.selectAll)
                                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                                    row.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                            else
                                for (String column : query.columns)
                                    row.put(column, resultSet.getObject(column));
                            fetchedAllRows.add(row);
                        }
                        query.importedData.put("results", fetchedAllRows);
                    }
                    query.importedData.put("messages", addMessage(response));

                    break;
                case CUD:
                case Update:
                case Create:
                    if (preparedStatement.executeUpdate() == 0)
                        response = HttpResponse.BAD_REQUEST;
                    else {
                        switch (query.queryType) {
                            case Update:
                                response = HttpResponse.OK;
                                break;
                            case Create:
                                response = HttpResponse.CREATED;
                                break;
                            default:
                                response = HttpResponse.NO_CONTENT;
                                break;
                        }
                    }
                    query.importedData.put("messages", addMessage(response));
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next())
                        query.importedData.put("results",
                                Collections.singletonList(
                                        Map.of("id", resultSet.getInt(1)
                                        )
                                )
                        );
                    break;
            }
            return new QBResults(query.importedData);
        } catch (SQLException e) {
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("message", "Error executing query: \n" + e.getMessage());
            messageMap.put("status_code", HttpResponse.INTERNAL_SERVER_ERROR.getCode());

            List<Map<String, Object>> messagesList = new ArrayList<>();
            messagesList.add(messageMap);

            query.importedData.put("messages", messagesList);

        }
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ignored) {
            query.importedData.put("messages", addMessage(HttpResponse.INTERNAL_SERVER_ERROR));
            return new QBResults(query.importedData);
        }

        return new QBResults(query.importedData);
    }

    private List<Map<String, Object>> addMessage(HttpResponse response) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("message", response.getMessage());
        messageMap.put("status_code", response.getCode());

        List<Map<String, Object>> messagesList = new ArrayList<>();
        messagesList.add(messageMap);
        return messagesList;
    }
}
