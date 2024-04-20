package org.example.socialmedia_proxy.Proxy;

import org.example.socialmedia_proxy.DB.Builder.Builder;
import org.example.socialmedia_proxy.DB.QueryBuilder;
import org.example.socialmedia_proxy.Model.UserProfile;

import java.util.Map;

public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public UserProfile getUserProfile(int userId) {
        Map<String, Object> userData = new QueryBuilder()
                .table("users")
                .select("username", "password", "name", "age")
                .where("id", userId)
                .build()
                .first();
        if(userData == null) return null;
        return new UserProfile(userData);
    }
}

