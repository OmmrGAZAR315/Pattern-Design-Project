package org.example.dpproject.Proxy;

import org.example.dpproject.Model.UserProfile;

import java.util.Map;
public interface Cacheable {
    Map<Integer, UserProfile> getUserProfileCache();

}

