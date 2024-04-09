package org.example.socialmedia_proxy.Proxy;

import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.UserProfileService;

import java.util.HashMap;
import java.util.Map;

public class UserProfileServiceProxy implements UserProfileService {
    private UserProfileService userProfileService;
    private Map<Integer, UserProfile> userProfileCache;

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
            userProfileCache.put(userId, userProfile);
            System.out.println("Fetching user profile from database for user ID: " + userId);
            return userProfile;
        }
    }
}
