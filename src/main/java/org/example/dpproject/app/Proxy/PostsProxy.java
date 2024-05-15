package org.example.dpproject.app.Proxy;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Services.PostService;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println("list " + Arrays.toString(recentPostsCache.toArray()));
        }
    }

    @Override
    public List<Object> getCache() {
        return recentPostsCache;
    }

    public static void setCookies(HttpServletResponse response) {
        String json = new PostsProxy().getCache().toString();
        String encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8);
        response.addCookie(new Cookie("recentPosts", encodedJson));
    }

    public static Post[] getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("recentPosts")) {
                String decodedJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                return new Post().convertJsonToPosts(decodedJson);
            }
        }
        return null;
    }
}
