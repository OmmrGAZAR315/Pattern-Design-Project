package org.example.socialmedia_proxy.DB_CRUD.Builder;

import org.example.socialmedia_proxy.DB_CRUD.QueryBuilder;

import java.util.List;
import java.util.Map;

public interface Builder {
    public Builder query = QueryBuilder.getQueryBuilder();

    public static List<Object> getResultsIDs() {
        return Query.parameters;
    }

    public static Map<String, Object[]> getImportedData() {
        return Query.importedData;
    }
    public Builder table(String tableName);

    public Builder select(String... columns);

    public Builder insert();

    public Builder call(String procedureName);

    public Builder setSelect(Object column);

    public Builder setInsertColumn(Object column);

    public Builder setInsertColumn();

    public Builder setInsertParameter(Object parameter);
    public QueryBuilder setInsertParameter();
    public Builder setCallParameter(String parameter);

    public Builder where(String column, Object value);

    public Builder where(String column, String operator, Object value);


    public Builder whereIn(String column, String[] values);

    public Builder whereNotIn(String column, String[] values);

    public Builder whereBetween(String column, String value1, String value2);

    public Builder whereNotBetween(String column, String value1, String value2);

    public Builder whereNull(String column);

    public Builder whereNotNull(String column);

    public Builder whereLike(String column, String value);

    public Builder whereNotLike(String column, String value);

//    public  void getMetaData(String tableName, String columnName, String value) throws SQLException;

    public void build();


}
