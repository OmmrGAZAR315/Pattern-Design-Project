package org.example.dpproject.app.Models;

import org.example.dpproject.DB.Relation;

import java.util.Map;

public class Comment {
    private int id;
    private String text;
    private String commentorid;
    private String postid;


    public Comment(String text, String commetor, String postid) {
        this.text = text;
        this.commentorid = commetor;
        this.postid = postid;
    }
    public Comment(Map<String, Object> comment) {
        this.id = (int) comment.get("id");
        this.text = (String) comment.get("user_id");
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

    public String getCommentorid() {
        return commentorid;
    }

    public void setCommentorid(String commentorid) {
        this.commentorid = commentorid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
