package org.example.dpproject.app.Proxy;

import org.example.dpproject.app.Model.UserProfile;

import java.util.Map;
public interface Cacheable {
    Map<Integer, UserProfile> getUserProfileCache();

}

