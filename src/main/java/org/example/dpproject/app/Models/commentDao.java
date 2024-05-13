package org.example.dpproject.app.Models;


import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class commentDao {
    List<Map<String, Object>> comments = new ArrayList<>();
    QueryBuilder queryBuilder = new QueryBuilder();


    public void addCommentToDatabase(comment comment) {

        queryBuilder.table("comments").insert("text", "commenter_id", "post_id")

                .setParameter(comment.getCommentText())
                .setParameter(comment.getCommentorid())
                .setParameter(comment.getPostid())
                .build();
    }

    public QBResults FetchCommentsForPost(String postId) {
        return queryBuilder.table("comments").select("text")
                .where("post_id", postId)
                .build();
    }

}
