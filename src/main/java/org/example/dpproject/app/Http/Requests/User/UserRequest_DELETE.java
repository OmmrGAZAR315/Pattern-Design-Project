package org.example.dpproject.app.Http.Requests.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Model.UserProfile;

public class UserRequest_DELETE extends UserRequest {
    public static UserProfile validate(HttpServletRequest request, HttpServletResponse response) {
        int id = checkID(request, response);
        if (id != -1) {
            UserProfile userProfile = new UserProfile();
            userProfile.setId(id);
            return userProfile;
        } else {
            return null;
        }
    }
}