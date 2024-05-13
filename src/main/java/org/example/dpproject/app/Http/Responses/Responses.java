package org.example.dpproject.app.Http.Responses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;

import java.util.Map;

public abstract class Responses {
    public static String errorPage;
    public static String successPage;
    public static int statusCode;
    public static String message;
    public static HttpResponse response;

    public Responses forwardInSuccess(String successPage) {
        Responses.successPage = successPage;
        return this;
    }

    public Responses forwardInError(String errorPage) {
        Responses.errorPage = errorPage;
        return this;
    }
    public abstract void dispatch(HttpServletRequest request, HttpServletResponse response, QBResults queryResult, String $action);

    public void anonymousFunctionInSuccessCase() {
        
    }
}