package org.example.dpproject.app.Http.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.ParametersType;
import org.example.dpproject.app.Helpers.RegexPattern;
import org.example.dpproject.app.Http.DTOs.UserDto;

public class UserValidation extends Validation {

    public static UserDto validate_delete_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());
        ParametersType id = ParametersType.required;
        requestErrors(id, userDto.isIdPassed(), "ID", userDto.getId(), "[1-9]\\d*");


        if (isValidated(request, response))
            return userDto;
        else
            return null;

    }

    public static UserDto validate_edit_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());
        int minimumPassedParameters = 2;
        ParametersType id = ParametersType.required;
        ParametersType name = ParametersType.optional;
        ParametersType username = ParametersType.optional;
        ParametersType password = ParametersType.optional;
        ParametersType age = ParametersType.optional;

        requestErrors(id, userDto.isIdPassed(), "ID", userDto.getId(), RegexPattern.ID.getPattern());
        requestErrors(name, userDto.isNamePassed(), "Name", userDto.getName(), RegexPattern.NAME.getPattern());
        requestErrors(username, userDto.isUsernamePassed(), "Username", userDto.getUsername(), RegexPattern.USERNAME.getPattern());
        requestErrors(password, userDto.isPasswordPassed(), "Password", userDto.getPassword(),
                RegexPattern.PASSWORD.getPattern());
        requestErrors(age, userDto.isAgePassed(), "Age", userDto.getAge(), RegexPattern.AGE.getPattern());

        if (isNotPassedMinimumParameters(minimumPassedParameters,userDto.getPassedParameterCounter()))
            errorCollection += "must at least pass " + minimumPassedParameters + " parameters";

        if (isValidated(request, response))
            return userDto;
        else
            return null;
    }

    public static UserDto validate_login_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());
        ParametersType username = ParametersType.required;
        ParametersType password = ParametersType.required;

        requestErrors(username, userDto.isUsernamePassed(), "Username", userDto.getUsername(), RegexPattern.USERNAME.getPattern());
        requestErrors(password, userDto.isPasswordPassed(), "Password", userDto.getPassword(),
                RegexPattern.NAME.getPattern());

        if (isValidated(request, response))
            return userDto;
        else
            return null;
    }

    public static UserDto validate_signUp_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());
        ParametersType username = ParametersType.required;
        ParametersType password = ParametersType.required;
        ParametersType name = ParametersType.optional;
        ParametersType age = ParametersType.optional;

        requestErrors(username, userDto.isUsernamePassed(), "Username", userDto.getUsername(),
                RegexPattern.USERNAME.getPattern());
        requestErrors(password, userDto.isPasswordPassed(), "Password", userDto.getPassword(),
                RegexPattern.PASSWORD.getPattern());
        requestErrors(name, userDto.isNamePassed(), "Name", userDto.getName(), RegexPattern.NAME.getPattern());
        requestErrors(age, userDto.isAgePassed(), "Age", userDto.getAge(), RegexPattern.AGE.getPattern());

        if (isValidated(request, response))
            return userDto;
        else
            return null;
    }
}