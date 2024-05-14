package org.example.dpproject.app.Http.DTOs;

import java.util.HashMap;
import java.util.Map;

public class CommentDto extends DTO {
    private final String text;
    private final String postId;
    private final String userId;

    public CommentDto(Map<String, String[]> parameters) {
        if (parameters.get("text") == null)
            this.text = null;
        else
            this.text = parameters.get("text")[0];
        if (parameters.get("postId") == null)
            this.postId = null;
        else
            this.postId = parameters.get("postId")[0];
        if (parameters.get("userId") == null)
            this.userId = null;
        else
            this.userId = parameters.get("userId")[0];
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if (text != null)
            map.put("text", text);
        if (postId != null)
            map.put("postId", postId);
        return map;
    }

    public boolean isCommentPassed() {
        return text != null;
    }

    public boolean isPostIdPassed() {
        return postId != null;
    }

    public boolean isUserIdPassed() {
        return userId != null;
    }


    public String getText() {
        return text;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }
}
