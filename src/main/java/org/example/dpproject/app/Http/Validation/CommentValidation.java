package org.example.dpproject.app.Http.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Helpers.ParametersType;
import org.example.dpproject.app.Http.DTOs.CommentDto;
import org.example.dpproject.app.Helpers.RegexPattern;

public class CommentValidation extends Validation {

    public static CommentDto validate_createComment_request(HttpServletRequest req, HttpServletResponse resp) {
        CommentDto commentDto = new CommentDto(req.getParameterMap());
        ParametersType text = ParametersType.required;
        ParametersType postId = ParametersType.required;
        ParametersType userId = ParametersType.required;
        requestErrors(text, commentDto.isCommentPassed(), "Text", commentDto.getText(), RegexPattern.CONTENT.getPattern());
        requestErrors(postId, commentDto.isPostIdPassed(), "PostId", commentDto.getPostId(), RegexPattern.ID.getPattern());
        requestErrors(userId, commentDto.isUserIdPassed(), "UserId", commentDto.getUserId(), RegexPattern.ID.getPattern());
        if (isValidated(req, resp))
            return commentDto;
        else
            return null;
    }
}
