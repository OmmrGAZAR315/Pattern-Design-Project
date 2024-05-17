package org.example.dpproject.DB;

public interface Relation {
    public <T> T[] hasMany(Class<T> clazz, String foreignKey, int primaryKey) ;

    public QBResults hasOne(String table, String foreignKey, int primaryKey) ;
}
