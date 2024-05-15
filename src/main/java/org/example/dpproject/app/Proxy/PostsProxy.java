package org.example.dpproject.app.Proxy;


import org.example.dpproject.app.Services.PostService;

import java.util.ArrayList;
import java.util.List;

public class PostsProxy implements Cacheable {
    private PostService postService;
    private static List<Object> recentPostsCache = new ArrayList<>();

    public PostsProxy() {
        this.postService = new PostService();
    }

    public void setRecentPosts() {
        if (recentPostsCache.isEmpty())
            recentPostsCache = postService.getRecentPosts(5);
        else {
            recentPostsCache.add(postService.getLastPost());
            for (int i = 0; i < 5; i++)
                recentPostsCache.set(i, recentPostsCache.get(i + 1));
            recentPostsCache.remove(5);
        }
    }

    @Override
    public List<Object> getCache() {
        return recentPostsCache;
    }
}
