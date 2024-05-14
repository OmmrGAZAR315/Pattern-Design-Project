package org.example.dpproject.app.Http.Responses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.util.Map;

public class Responses {
    private static String errorPage;
    private static String successPage;
   private static String page;

    public static HttpResponse response;

    protected static String getPage() {
        return page;
    }

    public Responses forwardInSuccess(String successPage) {
        Responses.successPage = successPage;
        return this;
    }

    public Responses forwardInError(String errorPage) {
        Responses.errorPage = errorPage;
        return this;
    }

    public void dispatch(HttpServletRequest request, HttpServletResponse response,
                         QBResults queryResult, String $action, int statusCode) {
        request.setAttribute("action", $action);

        try {
            Object msg;
            int code;

            if (queryResult == null) {
                msg = HttpResponse.INTERNAL_SERVER_ERROR.getMessage();
                code = HttpResponse.INTERNAL_SERVER_ERROR.getCode();
                page = errorPage;
            } else {
                if (queryResult.getStatusCode() != statusCode)
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
            request.setAttribute("message", msg);
            request.setAttribute("status_code", code);
            anonymousFunctionInSuccessCase();
            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void anonymousFunctionInSuccessCase() {
    }
}