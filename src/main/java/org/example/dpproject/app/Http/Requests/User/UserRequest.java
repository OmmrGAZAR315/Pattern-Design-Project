package org.example.dpproject.app.Http.Requests.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public abstract class UserRequest {
    public static int checkID(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (id != null &&
                id.matches("^[0-9]*$") &&
                0 < Integer.parseInt(id)) {

            return Integer.parseInt(id);
        } else
            return -1;
    }
    public static String ReturnCheck(Object ob, String errorMessage) {
        if (ob.equals(-1)|| ob.equals("-1")) {
            return "Invalid " + errorMessage + "\n";
        }
        return "";

    }
}
