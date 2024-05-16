package org.example.dpproject.app.DTOs;

import java.util.HashMap;
import java.util.Map;

public class PostDto extends DTO {
    private final String id;
    private String title;
    private String content;
    private String userId;
    protected int passedParameterCounter = 0;

    public PostDto(Map<String, String[]> parameters) {
        if (parameters.get("id") == null)
            this.id = null;
        else {
            this.id = parameters.get("id")[0];
            passedParameterCounter++;
        }
        if (parameters.get("title") == null)
            this.title = null;
        else {
            this.title = parameters.get("title")[0];
            passedParameterCounter++;
        }
        if (parameters.get("content") == null)
            this.content = null;
        else {
            this.content = parameters.get("content")[0];
            passedParameterCounter++;
        }
        if (parameters.get("userId") == null)
            this.userId = null;
        else {
            this.userId = parameters.get("userId")[0];
            passedParameterCounter++;
        }
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if (title != null)
            map.put("title", title);
        if (content != null)
            map.put("content", content);
        if (userId == null)
            map.put("userId", userId);
        return map;
    }

    public boolean isIdPassed() {
        return id != null;
    }

    public boolean isTitlePassed() {
        return title != null;
    }

    public boolean isContentPassed() {
        return content != null;
    }

    public boolean isUserIdPassed() {
        return userId != null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public int getPassedParameterCounter() {
        return passedParameterCounter;
    }
}