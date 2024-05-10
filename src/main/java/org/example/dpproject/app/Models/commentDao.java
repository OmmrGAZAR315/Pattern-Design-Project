package org.example.dpproject.app.Models;


import org.example.dpproject.DB.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class commentDao {
    List<Map<String,Object>> comments = new ArrayList<>();
    QueryBuilder queryBuilder = new QueryBuilder();


    public void addCommentToDatabase (comment comment) {

        queryBuilder.table("comments").insert("text","commenter_id","post_id")

                .setParameter(comment.getCommentText())
                .setParameter(comment.getCommentorid())
                .setParameter(comment.getPostid())
                .build();
    }

    public List<Map<String,Object>> FetchCommentsForPost (String postid) {
        comments = queryBuilder.table("comments").select("text")
                .where("post_id", postid)
                .build()
                .all();
        return comments;
    }

}
