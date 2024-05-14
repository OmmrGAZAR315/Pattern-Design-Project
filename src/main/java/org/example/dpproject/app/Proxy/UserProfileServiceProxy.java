package org.example.dpproject.app.Proxy;


import org.example.dpproject.app.Models.User;

import java.util.HashMap;
import java.util.Map;

public class UserProfileServiceProxy implements UserProfileService, Cacheable {
    private UserProfileService userProfileService;
    private static Map<Integer, User> userProfileCache;

    public UserProfileServiceProxy() {
        this.userProfileService = new UserProfileServiceImpl();
        this.userProfileCache = new HashMap<>();
    }

    @Override
    public User getUserProfile(int userId) {
        if (userProfileCache.containsKey(userId)) {
            System.out.println("Retrieving user profile from cache for user ID: " + userId);
            return userProfileCache.get(userId);
        } else {
            User user = userProfileService.getUserProfile(userId);
            if (user == null)
                return null;
            userProfileCache.put(userId, user);
            System.out.println("Fetching user profile from database for user ID: " + userId);
            return user;
        }
    }

    public Map<Integer, User> getUserProfileCache() {
        return userProfileCache;
    }
}
