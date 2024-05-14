package org.example.dpproject.app.DAOs;


import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Observer.PostObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class postDao {
    List<Map<String, Object>> Posts = new ArrayList<>();
    private List<PostObserver> observers = new ArrayList<>();

    public List<Map<String, Object>> fetchPosts() {
        return new QueryBuilder().
                table("posts").
                select("title", "content", "id", "postId").
                build().
                all();
    }

    public List<Map<String, Object>> fetchPostsOfUser(String usernameid) {
        return new QueryBuilder()
                .table("posts").
                select("title", "content", "id").
                where("id", usernameid).
                build().
                all();
    }

    public void savePost(Post post) {

        QueryBuilder query = new QueryBuilder();

        query.table("posts").insert("title",
                        "content", "id")

                .setParameter(post.getTitle())
                .setParameter(post.getContent()).
                setParameter(post.getUserId()).

                build();

    }

    public void notifyObservers() {

        for (PostObserver observer : observers) {
            observer.updateMessage();
        }

    }

    public void addObserver(PostObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PostObserver observer) {
        observers.remove(observer);
    }


}
