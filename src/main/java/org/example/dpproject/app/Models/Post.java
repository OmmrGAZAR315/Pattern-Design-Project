package org.example.dpproject.app.Models;


import org.example.dpproject.app.Observer.Observer;
import org.example.dpproject.app.Observer.PostObserver;

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

}
