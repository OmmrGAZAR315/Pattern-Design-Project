package org.example.dpproject.app.Http.Requests.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class UserRequest {
    public static int checkID(HttpServletRequest request, HttpServletResponse response)  {
        String id = request.getParameter("id");
        if (id != null &&
                id.matches("^[0-9]*$") &&
                Integer.parseInt(id) > 0) {

            return Integer.parseInt(id);
        } else {
            request.setAttribute("error", "Invalid ID");
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }catch (ServletException | IOException e){
                e.printStackTrace();
            }
            return -1;
        }
    }
}
