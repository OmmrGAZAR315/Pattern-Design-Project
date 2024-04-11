package org.example.socialmedia_proxy.Proxy;

import org.example.socialmedia_proxy.DB_CRUD.DB;
import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.UserProfileService;

public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public UserProfile getUserProfile(int userId) {
        // Fetch user profile from the database
        // Simulated database call
//        return DB.getUserProfile(userId);
        return new UserProfile("John Doe", "d");
    }
}

