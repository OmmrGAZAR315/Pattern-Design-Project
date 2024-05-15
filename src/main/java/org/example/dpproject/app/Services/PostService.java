package org.example.dpproject.app.Services;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.PostDto;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Proxy.Cacheable;
import org.example.dpproject.app.Repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService implements Cacheable {
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

    public List<Object> getRecentPosts(int limit) {
        QBResults qbResults = this.postRepository.getRecentPosts(limit);
        if (qbResults.getStatusCode() != HttpResponse.OK.getCode()) {
            System.out.println("getRecentPosts msg: " + qbResults.getMessage());
            System.out.println("getRecentPosts status code:" + qbResults.getStatusCode());
            return null;
        }
        List<Map<String, Object>> listPosts = qbResults.all();
        List<Object> posts = new ArrayList<>();
        for (int i = 0; i < listPosts.size(); i++)
            posts.add(new Post(listPosts.get(i)));
        return posts;
    }

    public Object getLastPost() {
        QBResults qbResults = this.postRepository.getRecentPosts(1);
        if (qbResults.getStatusCode() != HttpResponse.OK.getCode()) {
            System.out.println("getPostById msg: " + qbResults.getMessage());
            System.out.println("getPostById status code:" + qbResults.getStatusCode());
            return null;
        }
        return new Post(qbResults.first());
    }

    @Override
    public List<Object> getCache() {
        return List.of();
    }
}







