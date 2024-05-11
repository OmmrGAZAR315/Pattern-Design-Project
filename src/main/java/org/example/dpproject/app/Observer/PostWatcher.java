package org.example.dpproject.app.Observer;

import org.example.dpproject.app.Models.postDao;

public class PostWatcher implements PostObserver{

    private postDao postdao ;

    public PostWatcher(postDao postdao)
    {
        this.postdao = postdao ;
    }

    @Override
    public void updateMessage() {
        System.out.println("The Post list has been updated/Watched :p" ) ;
    }
}
