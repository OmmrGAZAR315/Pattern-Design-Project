package org.example.dpproject.app.Models;

import java.util.Map;

public class Comment {
    private int id;
    private String text;
    private int userId;
    private int postId;

    public Comment(Map<String, Object> userData) {
        System.out.println("userData: " + userData);
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
}
