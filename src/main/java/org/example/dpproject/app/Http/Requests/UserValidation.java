package org.example.dpproject.app.Http.Requests;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.ParametersType;
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

        requestErrors(id, userDto.isIdPassed(), "ID", userDto.getId(), "[1-9]\\d*");
        requestErrors(name, userDto.isNamePassed(), "Name", userDto.getName(), "[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*");
        requestErrors(username, userDto.isUsernamePassed(), "Username", userDto.getUsername(), "[a-zA-Z0-9_]{4,16}");
        requestErrors(password, userDto.isPasswordPassed(), "Password", userDto.getPassword(),
                "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}");
        requestErrors(age, userDto.isAgePassed(), "Age", userDto.getAge(), "(?:[1-9][0-9]?|1[01][0-9]|120)");

        if (userDto.isNotPassedMinimumParameters(minimumPassedParameters))
            errorCollection += "must at least pass " + minimumPassedParameters + " parameters";

        if (isValidated(request, response))
            return userDto;
        else
            return null;
    }

}