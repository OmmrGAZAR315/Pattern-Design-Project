package org.example.dpproject.app.Observer;

import org.example.dpproject.app.Models.Post;
import org.example.dpproject.app.Proxy.PostsProxy;

public class PostObserver implements Observer {

    @Override
    public void created() {
        new PostsProxy().setRecentPosts();
    }
}
