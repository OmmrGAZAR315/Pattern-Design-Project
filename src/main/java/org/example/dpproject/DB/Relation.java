package org.example.dpproject.DB;

public class Relation {
    public QBResults hasMany(String table, String foreignKey, String primaryKey) {
        return new QueryBuilder()
                .table(table)
                .where(foreignKey, primaryKey)
                .build();
    }
}
