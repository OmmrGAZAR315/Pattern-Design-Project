package org.example.dpproject.DB;


import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.Builder.Query;

import java.sql.*;
import java.util.*;

public class QueryBuilder implements Builder {
    public QueryBuilder() {
        Query.isCallParameterSet = false;
        Query.isWhereSet = false;
    }

    public QueryBuilder table(String tableName) {
        Query.tableName = "`" + tableName + "`";
        Query.resetBooleans();
        Query.columns.clear();
        Query.parameters.clear();
        return this;
    }

    public QueryBuilder select(String... columns) {
        String columnsSplitByComma = "id, ";
        if (columns.length == 1) {
            if (columns[0].equals("*"))
                Query.selectAll = true;

            columnsSplitByComma = columns[0];
        } else {
            columnsSplitByComma += String.join(", ", columns);
        }
        Query.query = "SELECT " + columnsSplitByComma + " FROM " + Query.tableName;
        Query.queryType = QueryType.READ;
        Query.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public QueryBuilder insert(String... columns) {
        Query.query = "INSERT INTO " + Query.tableName;
        Query.query += "( ";
        Query.query += String.join(",", columns);
        Query.query += ") VALUES ( ";

        Query.parameters.add(columns.length);
        Query.queryType = QueryType.Create;
        return this;
    }

    public QueryBuilder update(String... columns) {
        Query.query = "UPDATE " + Query.tableName;
        Query.query += " SET ";

        Query.columns.addAll(Arrays.asList(columns));
        Query.queryType = QueryType.Update;
        return this;
    }

    public QueryBuilder delete() {
        Query.query = "DELETE FROM " + Query.tableName;
        Query.queryType = QueryType.CUD;
        return this;
    }

    public QueryBuilder call(String procedureName) {
        Query.query = "{CALL " + procedureName;
        return this;
    }

    public QueryBuilder setParameter(Object parameter) {
        if (Query.queryType == QueryType.Create)
            setInsertParameter(parameter);

        else if (Query.queryType == QueryType.Update)
            setUpdateParameter(parameter);

        return this;
    }

    public void setInsertParameter(Object parameter) {
        if (Query.isParameterSet)
            Query.query += ", " + "?" + " ";
        else {
            Query.query += "?";
            Query.isParameterSet = true;
        }

        Query.parameters.add(parameter);
        int numberOfColumns = (int) Query.parameters.get(0);
        // if the number of columns is equal to the number of parameters
        if (Query.parameters.size() - 1 == numberOfColumns) {
            Query.query += ")";
            Query.parameters.remove(0);
        }
    }

    public void setUpdateParameter(Object parameter) {
        Query.query += Query.columns.get(0) + " = ?";
        Query.columns.remove(0);
        Query.parameters.add(parameter);

        if (!Query.columns.isEmpty())
            Query.query += ", ";

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
        return where(column, "=", value, "AND");
    }

    public QueryBuilder orWhere(String column, Object value) {
        return where(column, "=", value, "OR");
    }

    public QueryBuilder where(String column, String operator, Object value, String logicalOperator) {
        if (!Query.isWhereSet) {
            Query.query += " WHERE ";
            Query.isWhereSet = true;
        } else
            Query.query += " " + logicalOperator + " ";

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
                            for (String column : Query.columns)
                                row.put(column, resultSet.getObject(column));
                        fetchedAllRows.add(row);
                    }
                    Query.importedData.addAll(fetchedAllRows);

                    break;
                case CUD:
                case Update:
                case Create:
                    if (preparedStatement.executeUpdate() == 0)
                        Query.importedData.add(
                                Map.of("message", "No rows affected")
                        );
                    else
                        Query.importedData.add(
                                Map.of("message", "Success")
                        );
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next())
                        Query.importedData.add(
                                Map.of("id", resultSet.getInt(1)
                                )
                        );
                    break;
            }
            return this;
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: \n" + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, Object> first() {
        if (Query.importedData != null && !Query.importedData.isEmpty())
            return Query.importedData.get(0);

        return null;
    }

    @Override
    public Map<String, Object> last() {

        if (Query.importedData != null && !Query.importedData.isEmpty())
            return Query.importedData.get(Query.importedData.size() - 1);

        return null;
    }

    @Override
    public List<Map<String, Object>> all() {

        if (Query.importedData != null && !Query.importedData.isEmpty())
            return Query.importedData;

        return null;
    }

}
