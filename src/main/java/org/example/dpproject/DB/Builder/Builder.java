package org.example.dpproject.DB.Builder;

import org.example.dpproject.DB.QBResults;

import java.util.List;
import java.util.Map;


public interface Builder {

    Builder table(String tableName);

    Builder select(String... columns);

    Builder insert(String... columns);

    Builder update(String... columns);

    Builder delete();

    Builder call(String procedureName);

    Builder setParameter(Object parameter);

    void setInsertParameter(Object parameter);

    void setUpdateParameter(Object parameter);

    Builder setCallParameter(String parameter);

    Builder where(String column, Object value);

    Builder whereId(Object value);

    Builder orWhere(String column, Object value);

    Builder where(String column, String operator, Object value, String logicalOperator);


    Builder whereIn(String column, String[] values);

    Builder whereNotIn(String column, String[] values);

    Builder whereBetween(String column, String value1, String value2);

    Builder whereNotBetween(String column, String value1, String value2);

    Builder whereNull(String column);

    Builder whereNotNull(String column);

    Builder whereLike(String column, String value);

    Builder whereNotLike(String column, String value);

    Builder orderBy(String column);
    Builder orderBy(String column, String order);

    Builder limitBy(int limit);

//    public  void getMetaData(String tableName, String columnName, String value) throws SQLException;

    QBResults build();
}
