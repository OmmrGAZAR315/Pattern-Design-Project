package org.example.dpproject.app.Http.Requests.UserRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Models.UserProfile;

import java.io.IOException;

public class UserRequests extends Requests {
    static String errorCollection = "";

    public static UserDto validate_delete_request(HttpServletRequest request, HttpServletResponse response) {
        int id = checkID(request, response);

        errorCollection += ReturnChecker(id, "ID");
        if (!errorCollection.isEmpty()) {
            request.setAttribute("error", errorCollection);
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            errorCollection = "";
            return null;
        }

        return new UserDto()
                .setId(id);
    }
}