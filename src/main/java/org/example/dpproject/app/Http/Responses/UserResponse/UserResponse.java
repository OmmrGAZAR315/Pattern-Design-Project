package org.example.dpproject.app.Http.Responses.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.Responses.Responses;

import java.util.Map;
import java.util.Objects;

public class UserResponse extends Responses {

    public static void dispatch(HttpServletRequest request, HttpServletResponse response, Map<String, Object> queryResult, String $action) {
        try {
            if (Objects.equals(queryResult.get("status_code"), HttpResponse.OK.getCode())) {
                request.setAttribute("action", $action);

                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                if (queryResult.get("error") != null)
                    request.setAttribute("error", queryResult.get("error"));
                else request.setAttribute("error",
                        queryResult.get("message") + "\n "
                                + queryResult.get("status_code")
                );


                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception ignored) {
        }
    }


}