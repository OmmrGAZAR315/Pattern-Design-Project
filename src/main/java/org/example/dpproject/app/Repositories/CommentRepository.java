package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;

public class CommentRepository {
    private String table = "comments";

    public QBResults getAllComments() {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .build();
    }
}
