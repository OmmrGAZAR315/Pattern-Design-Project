package org.example.dpproject.DB;

import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.util.List;
import java.util.Map;

public interface Relation {
    public <T> T[] hasMany(Class<T> clazz, String foreignKey, int primaryKey) ;

    public QBResults hasOne(String table, String foreignKey, int primaryKey) ;
}
