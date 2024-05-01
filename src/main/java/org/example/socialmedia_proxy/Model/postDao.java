package org.example.socialmedia_proxy.Model;



import org.example.socialmedia_proxy.DB.QueryBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class postDao {


    public List<Map<String, Object>> fetchPosts ()
    {

        QueryBuilder q = new QueryBuilder();
        List<Map<String, Object>> Posts = q.
                table("posts").
                select("title","content").build().
                all();

        return Posts;
    }
}
