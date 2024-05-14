package org.example.dpproject.app.Proxy;

import org.example.dpproject.app.Models.User;

import java.util.Map;
public interface Cacheable {
    Map<Integer, User> getUserProfileCache();

}

