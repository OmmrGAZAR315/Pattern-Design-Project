package org.example.dpproject.app.Models;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.DB.Relation;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Observer.Observable;
import org.example.dpproject.app.Observer.Observer;

import java.util.List;
import java.util.Map;

public abstract class Model implements Relation, Observable {
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

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    public void unsubscribe(Observer observable) {
        subscribers.remove(observable);
    }

    @Override
    public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            subscriber.created();
        }
    }

}
