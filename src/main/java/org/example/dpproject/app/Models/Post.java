package org.example.dpproject.app.Models;


import org.example.dpproject.app.Observer.Observer;
import org.example.dpproject.app.Observer.PostObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Post extends Model {
    public static final String table = "posts";
    private static final Observer postObserver = new PostObserver();
    private int id;
    private String title;
    private String content;
    private int userId;


    public Post() {
    }

    public Post(int id, String title, String content, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Post(Map<String, Object> post) {
        this.id = (int) post.get("id");
        this.title = (String) post.get("title");
        this.content = (String) post.get("content");
        this.userId = (int) post.get("user_id");
    }

    public void Observe() {
        subscribe(postObserver);
        notifySubscribers();
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", title='" + title + "', content='" + content + "', userId=" + userId + "}";
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    //Relation One - Many with Comment
    public Comment[] comments() {
        return this.hasMany(Comment.class, "post_id", id);
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
            try {
                int id = Integer.parseInt(postString.split(", title=")[0].split("id=")[1]);
                String title = postString.split(", content=")[0].split(", title='")[1];
                String content = postString.split(", userId=")[0].split(", content='")[1];
                int userId = Integer.parseInt(postString.split(", userId=")[1].split("\\}")[0]); // Adjusted splitting logic

                // Create a Post object and add it to the list
                postList.add(new Post(id, title, content, userId));
            } catch (Exception e) {
                System.out.println("Error reading cookie: " + postString);
            }
        }

        // Convert the list to an array and return
        return postList.toArray(new Post[0]);
    }

}
