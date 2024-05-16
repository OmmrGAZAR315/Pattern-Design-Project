package org.example.dpproject.DB;


import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.Builder.Query;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.sql.*;
import java.util.*;

public class QueryBuilder implements Builder {
    Query queryOb;

    public QueryBuilder() {
        queryOb = new Query();
    }

    public QueryBuilder table(String tableName) {
        queryOb.tableName = "`" + tableName + "`";
        queryOb.columns.clear();
        queryOb.parameters.clear();
        return this;
    }

    public QueryBuilder select(String... columns) {
        String columnsSplitByComma = "id, ";
        if (columns.length == 1) {
            if (columns[0].equals("*"))
                queryOb.selectAll = true;

            columnsSplitByComma = columns[0];
        } else {
            columnsSplitByComma += String.join(", ", columns);
        }
        queryOb.query = "SELECT " + columnsSplitByComma + " FROM " + queryOb.tableName;
        queryOb.queryType = QueryType.READ;
        queryOb.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public QueryBuilder insert(String... columns) {
        queryOb.query = "INSERT INTO " + queryOb.tableName;
        queryOb.query += "( ";
        queryOb.query += String.join(",", columns);
        queryOb.query += ") VALUES ( ";

        queryOb.parameters.add(columns.length);
        queryOb.queryType = QueryType.Create;
        return this;
    }

    public QueryBuilder update(String... columns) {
        queryOb.query = "UPDATE " + queryOb.tableName;
        queryOb.query += " SET ";

        queryOb.columns.addAll(Arrays.asList(columns));
        queryOb.queryType = QueryType.Update;
        return this;
    }

    public QueryBuilder delete() {
        queryOb.query = "DELETE FROM " + queryOb.tableName;
        queryOb.queryType = QueryType.CUD;
        return this;
    }

    public QueryBuilder call(String procedureName) {
        queryOb.query = "{CALL " + procedureName;
        return this;
    }

    public QueryBuilder setParameter(Object parameter) {
        if (queryOb.queryType == QueryType.Create)
            setInsertParameter(parameter);

        else if (queryOb.queryType == QueryType.Update)
            setUpdateParameter(parameter);

        return this;
    }

    public void setInsertParameter(Object parameter) {
        if (queryOb.isParameterSet)
            queryOb.query += ", " + "?" + " ";
        else {
            queryOb.query += "?";
            queryOb.isParameterSet = true;
        }

        queryOb.parameters.add(parameter);
        int numberOfColumns = (int) queryOb.parameters.get(0);
        // if the number of columns is equal to the number of parameters
        if (queryOb.parameters.size() - 1 == numberOfColumns) {
            queryOb.query += ")";
            queryOb.parameters.remove(0);
        }
    }

    public void setUpdateParameter(Object parameter) {
        queryOb.query += queryOb.columns.get(0) + " = ?";
        queryOb.columns.remove(0);
        queryOb.parameters.add(parameter);

        if (!queryOb.columns.isEmpty())
            queryOb.query += ", ";

    }

    public QueryBuilder setCallParameter(String parameter) {
        if (Objects.equals(parameter, "0"))
            queryOb.query += ")";

        else {
            if (!queryOb.isCallParameterSet) {
                queryOb.query += "(" + parameter + " ";
                queryOb.isCallParameterSet = true;
            } else
                queryOb.query += ", " + parameter + " ";

            queryOb.parameters.add(parameter);

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
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " " + logicalOperator + " ";

        queryOb.query += column + operator + " ? ";
        queryOb.parameters.add(value);
        return this;
    }

    public QueryBuilder whereIn(String column, String[] values) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";

        queryOb.query += column + " IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereNotIn(String column, String[] values) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";

        queryOb.query += column + " NOT IN (" + String.join(", ", values) + ")";
        return this;

    }

    public QueryBuilder whereBetween(String column, String value1, String value2) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";
        queryOb.query += column + " BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNotBetween(String column, String value1, String value2) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";
        queryOb.query += column + " NOT BETWEEN " + value1 + " AND " + value2;
        return this;

    }

    public QueryBuilder whereNull(String column) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";
        queryOb.query += column + " IS NULL";
        return this;

    }

    public QueryBuilder whereNotNull(String column) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";
        queryOb.query += column + " IS NOT NULL";
        return this;
    }

    public QueryBuilder whereLike(String column, String value) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";

        queryOb.query += column + " LIKE " + value;
        return this;
    }

    public QueryBuilder whereNotLike(String column, String value) {
        if (!queryOb.isWhereSet) {
            queryOb.query += " WHERE ";
            queryOb.isWhereSet = true;
        } else
            queryOb.query += " AND ";

        queryOb.query += column + " NOT LIKE " + value;
        return this;
    }

    @Override
    public Builder orderBy(String column) {
        return orderBy(column, "ASC");

    }

    @Override
    public Builder orderBy(String column, String order) {
        queryOb.query += " ORDER BY " + column + " " + order;
        return this;
    }

    @Override
    public Builder limitBy(int limit) {
        queryOb.query += " LIMIT " + limit;
        return this;
    }

    public QBResults build() {
//        System.out.println(queryOb.query);
        ResultSet resultSet = null;
        if (DB.getConnection() != null)
            try (PreparedStatement preparedStatement = DB.getConnection().prepareStatement(queryOb.query, Statement.RETURN_GENERATED_KEYS)) {
                if (!queryOb.parameters.isEmpty()) {
                    for (int i = 0; i < queryOb.parameters.size(); i++) {
                        preparedStatement.setObject(i + 1, queryOb.parameters.get(i));
                    }
                }
                HttpResponse response;
                queryOb.parameters.clear();
                switch (queryOb.queryType) {
                    case READ:
                        try {
                            resultSet = preparedStatement.executeQuery();
                            response = HttpResponse.OK;
                        } catch (Exception e) {
                            response = HttpResponse.BAD_REQUEST;
                            queryOb.importedData.put("messages", addMessage(response));
                            return new QBResults(queryOb.importedData);
                        }
                        List<Map<String, Object>> fetchedAllRows = new ArrayList<>();
                        if (!resultSet.isBeforeFirst())
                            response = HttpResponse.NOT_FOUND;
                        else {
                            while (resultSet.next()) {
                                Map<String, Object> row = new HashMap<>();
                                if (queryOb.selectAll)
                                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                                        row.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                                else
                                    for (String column : queryOb.columns)
                                        row.put(column, resultSet.getObject(column));
                                fetchedAllRows.add(row);
                            }
                            queryOb.importedData.put("results", fetchedAllRows);
                        }
                        queryOb.importedData.put("messages", addMessage(response));

                        break;
                    case CUD:
                    case Update:
                    case Create:
                        if (preparedStatement.executeUpdate() == 0)
                            response = HttpResponse.BAD_REQUEST;
                        else {
                            switch (queryOb.queryType) {
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
                        queryOb.importedData.put("messages", addMessage(response));
                        resultSet = preparedStatement.getGeneratedKeys();
                        if (resultSet.next())
                            queryOb.importedData.put("results",
                                    Collections.singletonList(
                                            Map.of("id", resultSet.getInt(1)
                                            )
                                    )
                            );
                        break;
                }
                return new QBResults(queryOb.importedData);
            } catch (SQLException e) {
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("message", "Error executing query: \n" + e.getMessage());
                messageMap.put("status_code", HttpResponse.INTERNAL_SERVER_ERROR.getCode());

                List<Map<String, Object>> messagesList = new ArrayList<>();
                messagesList.add(messageMap);

                queryOb.importedData.put("messages", messagesList);

            }
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ignored) {
            queryOb.importedData.put("messages", addMessage(HttpResponse.INTERNAL_SERVER_ERROR));
            return new QBResults(queryOb.importedData);
        }

        return new QBResults(queryOb.importedData);
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
