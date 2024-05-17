package org.example.dpproject.app.Observer;


import org.example.dpproject.app.Proxy.CommentsProxy;

public class CommentObserver implements Observer {

    @Override
    public void created() {
        new CommentsProxy().setRecentPostsComments();
    }
}
