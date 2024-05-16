package org.example.dpproject.app.Proxy;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.Comment;
import org.example.dpproject.app.Services.CommentService;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CommentsProxy implements Cacheable {
    private CommentService commentService;
    private static Map<Integer, Object> recentPostsCommentsCache = new HashMap<>();

    public CommentsProxy() {
        this.commentService = new CommentService();
    }

    public void setRecentPostsComments() {
        if (recentPostsCommentsCache.isEmpty())
            recentPostsCommentsCache = commentService.getRecentPostsComments(5);
        if (recentPostsCommentsCache == null) {
            System.out.println("No comments found. proxy");
            return;
        } else {
            Comment[] comments = commentService.getLastPostComments();
            if (comments == null) {
                System.out.println("No comments found.");
                return;
            }
            recentPostsCommentsCache.put(comments[0].getPostId(), comments);
            recentPostsCommentsCache.remove(findLeastKey(recentPostsCommentsCache));
            System.out.println("list " + Arrays.toString(recentPostsCommentsCache.entrySet().toArray()));
        }
    }

    @Override
    public List<Object> getCache() {
        return null;
    }

    @Override
    public Map<Integer, Object> getMapCache() {
        return recentPostsCommentsCache;
    }

    public static void setCookies(HttpServletResponse response) {
        if (recentPostsCommentsCache==null || recentPostsCommentsCache.isEmpty())
            return;
        String commentsJson = new CommentsProxy().getMapCache().toString();
        System.out.println("commentsJson " + commentsJson);
        String commentsEncodedJson = URLEncoder.encode(commentsJson, StandardCharsets.UTF_8);
        response.addCookie(new Cookie("recentPostsComments", commentsEncodedJson));
    }

    public static Comment[] getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("recentPostsComments")) {
                String decodedJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                return new Comment().convertJsonToComments(decodedJson, new CommentsProxy().getMapCache());
            }
        }
        return null;
    }

    public static int findLeastKey(Map<Integer, Object> map) {
        // Get the set of keys from the map
        Set<Integer> keySet = map.keySet();

        // Initialize the minimum key to be the first key in the map
        int minKey = Integer.MAX_VALUE;

        // Iterate through the keys to find the minimum key
        for (int key : keySet) {
            if (key < minKey) {
                minKey = key;
            }
        }

        return minKey;
    }
}
