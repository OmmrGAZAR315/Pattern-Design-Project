package org.example.dpproject.app.Http.Requests.UserRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;

public class UserRequest extends Requests {

    public static UserDto validate_delete_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());

        boolean id = checkID(userDto.getId());

        errorCollection += validation(id, "ID");


        if (isValidated(request, response))
            return userDto;
        else
            return null;

    }

    public static UserDto validate_edit_request(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = new UserDto(request.getParameterMap());

        boolean id = checkID(userDto.getId());

        errorCollection += validation(id, "ID");

        if (isValidated(request, response))
            return userDto;
        else
            return null;
    }

}