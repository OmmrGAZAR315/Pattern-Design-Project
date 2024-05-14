package org.example.dpproject.app.Http.DTOs;

import java.util.Map;

public abstract class DTO {
    protected int passedParameterCounter = 0;
    public abstract Map<String, String> toMap();
    public int getPassedParameterCounter() {
        return passedParameterCounter;
    }

}
