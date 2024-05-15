package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Http.DTOs.PostDto;
import org.example.dpproject.app.Models.Post;

import java.util.Date;

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
                .insert("title", "content", "user_id", "created_at")
                .setParameter(dto.getTitle())
                .setParameter(dto.getContent())
                .setParameter(dto.getUserId())
                .setParameter(new Date())
                .build();
    }

    public QBResults getRecentPosts(int limit) {
        return new QueryBuilder()
                .table(table)
                .select("*")
                .orderBy("created_at", "DESC")
                .limitBy(limit)
                .build();
    }
}
