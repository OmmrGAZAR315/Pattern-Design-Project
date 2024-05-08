package org.example.dpproject.app.Requests.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserRequest_DELETE extends UserRequest {
    public static void validate(HttpServletRequest request, HttpServletResponse response) {
        checkID(request, response);
    }
}