package org.example.dpproject.app.Models;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.DB.Relation;
import org.example.dpproject.app.Helpers.HelperClass;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Observer.Observable;
import org.example.dpproject.app.Observer.Observer;

import java.util.ArrayList;
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

    public Post[] convertJsonToPosts(String decodedJson) {
        // Remove '[' and ']' characters from the beginning and end
        decodedJson = decodedJson.substring(1, decodedJson.length() - 1);

        // Split the string by '}, ' to separate individual posts
        String[] postStrings = decodedJson.split("\\}, ");

        // Create a list to store Post objects
        List<Post> postList = new ArrayList<>();

        // Iterate through each substring representing a post
        for (String postString : postStrings) {
            // Trim any leading or trailing whitespace
            postString = postString.trim();

            // Extract values for id, title, content, and userId
            int id = Integer.parseInt(postString.split(", title=")[0].split("id=")[1]);
            String title = postString.split(", content=")[0].split(", title='")[1];
            String content = postString.split(", userId=")[0].split(", content='")[1];
            int userId = Integer.parseInt(postString.split(", userId=")[1].split("\\}")[0]); // Adjusted splitting logic

            // Create a Post object and add it to the list
            postList.add(new Post(id, title, content, userId));
        }

        // Convert the list to an array and return
        return postList.toArray(new Post[0]);
    }


}
