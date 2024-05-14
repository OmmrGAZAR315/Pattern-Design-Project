package org.example.dpproject.DB;

public class Relation {
    public QBResults hasMany(String table, String foreignKey, int primaryKey) {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .where(foreignKey, primaryKey)
                .build();

    }
}
