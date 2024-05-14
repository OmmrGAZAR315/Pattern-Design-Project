package org.example.dpproject.app.Models;

import org.example.dpproject.DB.Relation;
import org.example.dpproject.app.Helpers.HelperClass;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Post extends Relation {

    private int id;
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

    public Post(Map<String, Object> post) {
        this.id = (int) post.get("id");
        this.userId = (String) post.get("user_id");
        this.title = (String) post.get("title");
        this.content = (String) post.get("content");
    }


    public int getId() {
        return id;
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

    public Comment[] comments() {
        List<Map<String, Object>> commentsList = this.hasMany("comments", "posts_id", "id").all();
       return HelperClass.convertListMapToArray(Comment.class, commentsList);
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
