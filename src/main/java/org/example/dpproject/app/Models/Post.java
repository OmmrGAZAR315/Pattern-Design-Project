package org.example.dpproject.app.Models;

import java.util.Date;

public class Post {

    private int postId;
    private String title;
    private String content;
    private Date createdDate;
    private String userId;
    private int groupid;




    public Post(String title, String content, String userID) {

        this.title = title;
        this.content = content;
        this.userId = userID;
        this.groupid = groupid;

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

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
