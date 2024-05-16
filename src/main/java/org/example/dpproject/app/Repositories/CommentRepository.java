package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.DTOs.CommentDto;

public class CommentRepository {
    private final String table = "comments";

    public QBResults getAllComments() {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .build();
    }

    public QBResults createComment(CommentDto commentDto) {
        return new QueryBuilder()
                .table(table)
                .insert("text", "post_id", "user_id")
                .setParameter(commentDto.getText())
                .setParameter(commentDto.getPostId())
                .setParameter(commentDto.getUserId())
                .build();
    }
}
