package org.example.dpproject.app.Requests.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class UserRequest {
    public static void checkID(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") == null) {
            try {
                response.sendError(400, "Missing id parameter");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
