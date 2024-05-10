package org.example.dpproject.app.Proxy;


import org.example.dpproject.app.Models.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class UserProfileServiceProxy implements UserProfileService, Cacheable {
    private UserProfileService userProfileService;
    private static Map<Integer, UserProfile> userProfileCache;

    public UserProfileServiceProxy() {
        this.userProfileService = new UserProfileServiceImpl();
        this.userProfileCache = new HashMap<>();
    }

    @Override
    public UserProfile getUserProfile(int userId) {
        if (userProfileCache.containsKey(userId)) {
            System.out.println("Retrieving user profile from cache for user ID: " + userId);
            return userProfileCache.get(userId);
        } else {
            UserProfile userProfile = userProfileService.getUserProfile(userId);
            if (userProfile == null)
                return null;
            userProfileCache.put(userId, userProfile);
            System.out.println("Fetching user profile from database for user ID: " + userId);
            return userProfile;
        }
    }

    public Map<Integer, UserProfile> getUserProfileCache() {
        return userProfileCache;
    }
}
