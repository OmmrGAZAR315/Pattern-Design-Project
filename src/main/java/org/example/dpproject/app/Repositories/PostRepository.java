package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Http.DTOs.PostDto;

public class PostRepository {
    private String table = "posts";

    public QBResults getAllPosts() {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .build();
    }

    public QBResults createPost(PostDto dto) {
        return new QueryBuilder()
                .table(table)
                .insert("title", "content")
                .setParameter(dto.getTitle())
                .setParameter(dto.getContent())
                .build();
    }
}
