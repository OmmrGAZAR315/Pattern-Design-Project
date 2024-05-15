package org.example.dpproject.app.Observer;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    List<Observer> subscribers = new ArrayList<>();

    public void subscribe(Observer observable);

    public void unsubscribe(Observer observable);

    public void notifySubscribers();
}
