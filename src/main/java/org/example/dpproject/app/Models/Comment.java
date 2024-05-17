package org.example.dpproject.app.Models;

import org.example.dpproject.app.Observer.Observer;
import org.example.dpproject.app.Observer.PostObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Comment extends Model {
    public static final String table = "comments";
    private static final Observer commentObserver = new PostObserver();

    private int id;
    private String text;
    private int userId;
    private int postId;

    public Comment() {
    }

    public Comment(int id, String text, int userId, int postId) {
        this.id = id;
        this.text = text;
        this.postId = postId;
        this.userId = userId;
    }

    public Comment(Map<String, Object> userData) {
        if (userData.get("id") == null)
            this.id = 0;
        else
            this.id = (int) userData.get("id");
        if (userData.get("user_id") == null)
            this.userId = 0;
        else
            this.userId = (int) userData.get("user_id");
        if (userData.get("post_id") == null)
            this.postId = 0;
        else
            this.postId = (int) userData.get("post_id");

        if (userData.get("text") == null)
            this.text = "null";
        else
            this.text = (String) userData.get("text");
    }

    public String toString(Map<Integer, Comment[]> commentMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Map.Entry<Integer, Comment[]> entry : commentMap.entrySet()) {
            sb.append(entry.getKey()).append("=[");

            Comment[] comments = entry.getValue();
            for (int i = 0; i < comments.length; i++) {
                sb.append(comments[i]);
                if (i < comments.length - 1) {
                    sb.append(", ");
                }
            }

            sb.append("]");
            if (entry.getKey() < commentMap.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public Comment[] convertJsonToComments(String decodedJson, Map<Integer, Object> commentMap) {
        // Create a list to store Comment objects
        List<Comment> commentList = new ArrayList<>();

        // Iterate through the map entries
        for (Map.Entry<Integer, Object> entry : commentMap.entrySet()) {
            int postId = entry.getKey();
            Object value = entry.getValue();

            // Check if the value is an array of Comment objects
            if (value instanceof Comment[]) {
                Comment[] comments = (Comment[]) value;

                // Add each Comment object to the list
                for (Comment comment : comments) {
                    commentList.add(comment);
                }
            }
        }

        // Convert the list to an array and return
        return commentList.toArray(new Comment[0]);
    }

    public void Observe() {
        subscribe(commentObserver);
        notifySubscribers();
    }

}
