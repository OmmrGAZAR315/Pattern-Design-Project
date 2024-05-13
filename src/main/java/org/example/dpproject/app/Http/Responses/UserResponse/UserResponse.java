package org.example.dpproject.app.Http.Responses.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Models.UserProfile;


public class UserResponse extends Responses {

    public static void login(HttpServletRequest request, HttpServletResponse response, QBResults qbResults) {
        UserResponse userResponse = new UserResponse() {
            public void anonymousFunctionInSuccessCase() {
                HttpSession session = request.getSession();
                session.setAttribute("user", new UserProfile(qbResults.first()));
                session.setAttribute("authenticated", true);
            }
        };
        userResponse
                .forwardInSuccess("home.jsp")
                .forwardInError("error.jsp")
                .dispatch(request, response, qbResults, "login");

    }

    public void dispatch(HttpServletRequest request, HttpServletResponse response,
                         QBResults queryResult, String $action) {
        request.setAttribute("action", $action);

        try {
            String page;
            Object msg;
            int code;

            if (queryResult == null) {
                msg = HttpResponse.INTERNAL_SERVER_ERROR.getMessage();
                code = HttpResponse.INTERNAL_SERVER_ERROR.getCode();
                page = errorPage;
            } else {
                if (queryResult.getStatusCode() != HttpResponse.OK.getCode())
                    page = errorPage;
                else
                    page = successPage;

                if (queryResult.getCustom_message() != null)
                    msg = queryResult.getCustom_message();
                else
                    msg = queryResult.getMessage();
                System.out.println(queryResult.getMessage());
                code = queryResult.getStatusCode();

            }
            request.setAttribute("message", (String) msg);
            request.setAttribute("status_code", code);
            anonymousFunctionInSuccessCase();
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}