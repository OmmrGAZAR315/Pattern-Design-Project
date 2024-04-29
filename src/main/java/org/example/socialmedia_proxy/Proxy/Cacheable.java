package org.example.socialmedia_proxy.Proxy;

import org.example.socialmedia_proxy.Model.UserProfile;

import java.util.Map;
public interface Cacheable {
    Map<Integer, UserProfile> getUserProfileCache();

}

