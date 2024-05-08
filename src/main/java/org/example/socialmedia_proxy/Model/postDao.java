package org.example.socialmedia_proxy.Model;



import org.example.socialmedia_proxy.DB.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class postDao  {


    QueryBuilder q = new QueryBuilder();
    List<Map<String,Object>> Posts = new ArrayList<>();


    public List<Map<String, Object>> fetchPosts ()
    {

            Posts = q.
                    table("posts").
                    select("title", "content", "id").
                    build().
                    all();
          return Posts;


    }

    public List<Map<String, Object>> fetchPostsOfUser (String usernameid)
    {


            List<Map<String, Object>> Posts = q.
                    table("posts").
                    select("title","content","id").
                    where("id" , usernameid).
                    build().
                    all();


        return Posts;
    }

    public void savePost(Post post) {

        QueryBuilder query = new QueryBuilder();

        query.table("posts").insert("title",
                        "content","id")

                .setParameter(post.getTitle())
                .setParameter(post.getContent()).
                setParameter(post.getUserId()).

                build();

    }














}
