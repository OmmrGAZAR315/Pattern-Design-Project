package org.example.dpproject.app.Observer;

import org.example.dpproject.app.Proxy.CommentsProxy;
import org.example.dpproject.app.Proxy.PostsProxy;

public class PostObserver implements Observer {

    @Override
    public void created() {
        new PostsProxy().setRecentPosts();
        new CommentsProxy().setRecentPostsComments();
    }
}
