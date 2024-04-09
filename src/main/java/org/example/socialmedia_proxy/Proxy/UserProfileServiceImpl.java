package org.example.socialmedia_proxy.Proxy;

import org.example.socialmedia_proxy.DB_CRUD.UserProfile_DBCRUD;
import org.example.socialmedia_proxy.Model.UserProfile;
import org.example.socialmedia_proxy.UserProfileService;

public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public UserProfile getUserProfile(int userId) {
        // Fetch user profile from the database
        // Simulated database call
        return UserProfile_DBCRUD.getUserProfile_DBCRUD().getUserProfile(userId);
    }
}

