package org.example.socialmedia_proxy.Model;

import java.util.Date;

public class Post {

    private int postId;
    private String title;
    private String content;
    private Date createdDate;
    private String userId;
    private String help;

    public Post(String title, String content, String userID) {

        this.title = title;
        this.content = content;
        this.userId = userID;

    }

    public int getPostId() {
        return postId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
