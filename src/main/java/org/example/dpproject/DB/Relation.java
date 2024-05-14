package org.example.dpproject.DB;

import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.util.List;
import java.util.Map;

public class Relation {
    public <T> T[] hasMany(Class<T> clazz, String foreignKey, int primaryKey) {
        String table;
        try {
             table = (String) clazz.getDeclaredField("table").get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        QBResults qbResults = new QueryBuilder()
                .table(table)
                .select("*")
                .where(foreignKey, primaryKey)
                .build();

        if (qbResults.getStatusCode() != HttpResponse.OK.getCode())
            return null;
        List<Map<String, Object>> commentsList = qbResults.all();
        return HelperClass.convertListMapToArray(clazz, commentsList);
    }

    public QBResults hasOne(String table, String foreignKey, int primaryKey) {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .where(foreignKey, primaryKey)
                .build();

    }
}
