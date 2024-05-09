package org.example.socialmedia_proxy.Model;

public class comment {
    private int commentId;
    private String commentText;
    private String commentorid;
    private String postid;


    public comment(String commentText, String commetor, String postid) {

        this.commentText = commentText;
        this.commentorid = commetor;
        this.postid = postid;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
