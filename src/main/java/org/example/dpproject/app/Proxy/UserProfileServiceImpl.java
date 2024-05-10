package org.example.dpproject.app.Proxy;


import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Models.UserProfile;

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

