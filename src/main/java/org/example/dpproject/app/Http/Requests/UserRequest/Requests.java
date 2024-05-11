package org.example.dpproject.app.Http.Requests.UserRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public abstract class Requests {
    static String errorCollection = "";

    public static boolean checkID(String id) {
        try {
            return 0 < Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String validation(boolean ob, String obName) {
        if (!ob)
            return "Invalid " + obName + "\n";
        else
            return "";

    }

    public static boolean isValidated(HttpServletRequest request, HttpServletResponse response) {
        if (!errorCollection.isEmpty()) {
            request.setAttribute("error", errorCollection);
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            errorCollection = "";
            return false;
        }
        return true;
    }
}
