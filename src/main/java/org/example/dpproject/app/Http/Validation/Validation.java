package org.example.dpproject.app.Http.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.ParametersType;

import java.io.IOException;


public abstract class Validation {
    public static String errorCollection = "";

    public static void requestErrors(ParametersType parameterType, boolean isParameterPassed,
                                     String parameterName,String parameter,String regexPattern) {
        boolean isParameterNotValidate = false;
        if (isParameterPassed)
            isParameterNotValidate = !parameterValidation_with_RegexPattern(parameter, regexPattern);

        if (parameterType.isRequired()) {
            if (!isParameterPassed) {
                errorCollection += parameterName + " is missing.<br>";
            } else if (isParameterNotValidate) {
                errorCollection += parameterName + " is not valid.<br>";
            }
        } else if (isParameterPassed && isParameterNotValidate) {
            errorCollection += parameterName + " is not valid.<br>";
        }
    }


    public static boolean parameterValidation_with_RegexPattern(String str, String regexPattern) {
        return str.matches(regexPattern);
    }

    public static boolean isValidated(HttpServletRequest request, HttpServletResponse response) {
        if (!errorCollection.isEmpty()) {
            request.setAttribute("message", errorCollection);
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

    public static boolean isNotPassedMinimumParameters(int minimumPassedParameters, int passedParameterCounter) {
        return passedParameterCounter < minimumPassedParameters;
    }
}
