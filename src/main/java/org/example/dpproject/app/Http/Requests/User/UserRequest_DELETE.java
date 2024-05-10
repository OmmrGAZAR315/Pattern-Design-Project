package org.example.dpproject.app.Http.Requests.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.UserProfile;

import java.io.IOException;

public class UserRequest_DELETE extends UserRequest {
    public static UserProfile validate(HttpServletRequest request, HttpServletResponse response) {
        int id = checkID(request, response);

        String errorCollection = "";
        errorCollection  +=  ReturnCheck(id, "ID");
        if (!errorCollection.isEmpty()) {
            request.setAttribute("error", errorCollection);
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        return userProfile;
    }
}