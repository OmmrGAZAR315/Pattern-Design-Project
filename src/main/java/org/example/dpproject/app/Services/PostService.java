package org.example.dpproject.app.Services;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.PostDto;
import org.example.dpproject.app.Repositories.PostRepository;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepository();
    }

    public QBResults getAllPosts() {
        QBResults results = this.postRepository.getAllPosts();
        if (results.getStatusCode() != HttpResponse.OK.getCode())
            return results.setCustom_message("no posts found");
        return results;
    }

    public QBResults createPost(PostDto dto) {
        QBResults results = this.postRepository.createPost(dto);
        if (results.getStatusCode() != HttpResponse.CREATED.getCode())
            return results.setCustom_message("post not created");
        return results;
    }
}







