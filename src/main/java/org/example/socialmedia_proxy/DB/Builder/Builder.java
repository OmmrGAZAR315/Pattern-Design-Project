package org.example.socialmedia_proxy.DB.Builder;

import org.example.socialmedia_proxy.DB.QueryBuilder;

import java.util.List;
import java.util.Map;

public interface Builder {
    public Builder query = QueryBuilder.getQueryBuilder();

    public static List<Object> getResultsIDs() {
        return Query.parameters;
    }

    public static Map<String, List<Map<String, Object>>> getImportedData() {
        return Query.importedData;
    }

    public Builder table(String tableName);

    public Builder select(String... columns);

    public Builder insert(String... columns);
    public Builder update(String... columns);
    public Builder delete();

    public Builder call(String procedureName);

    public Builder setInsertParameter(Object parameter);
    public QueryBuilder setUpdateParameter(Object parameter);

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

    public Builder build();

    public Map<String, Object> first();

    public Map<String, Object> last();

    public List<Map<String, Object>> all();

}
