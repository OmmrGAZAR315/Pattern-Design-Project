package org.example.dpproject.app.Http.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.ParametersType;
import org.example.dpproject.app.Helpers.RegexPattern;
import org.example.dpproject.app.Http.DTOs.PostDto;

public class PostValidation extends Validation {

    public static PostDto validate_creatPost_request(HttpServletRequest request, HttpServletResponse response) {
        PostDto postDto = new PostDto(request.getParameterMap());
        ParametersType title = ParametersType.required;
        ParametersType content = ParametersType.required;
        ParametersType userId = ParametersType.required;
        requestErrors(title, postDto.isTitlePassed(), "Title", postDto.getTitle(), RegexPattern.TITLE.getPattern());
        requestErrors(content, postDto.isContentPassed(), "Content", postDto.getContent(), RegexPattern.CONTENT.getPattern());
        requestErrors(userId, postDto.isUserIdPassed(), "User Id", postDto.getUserId(), RegexPattern.ID.getPattern());

        if (isValidated(request, response))
            return postDto;
        else
            return null;
    }
}
