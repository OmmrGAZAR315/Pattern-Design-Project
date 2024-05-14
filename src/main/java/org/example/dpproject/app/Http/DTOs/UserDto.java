package org.example.dpproject.app.Http.DTOs;

import java.util.HashMap;
import java.util.Map;

public class UserDto extends DTO{
    private final String id;
    private final String username;
    private String password;
    private final String name;
    private final String age;
    private int passedParameterCounter = 0;

    public UserDto(Map<String, String[]> parameters) {
        if (parameters.get("id") == null)
            this.id = null;
        else {
            this.id = parameters.get("id")[0];
            passedParameterCounter++;
        }
        if (parameters.get("username") == null)
            this.username = null;
        else {
            this.username = parameters.get("username")[0];
            passedParameterCounter++;
        }
        if (parameters.get("password") == null)
            this.password = null;
        else {
            this.password = parameters.get("password")[0];
            passedParameterCounter++;

        }
        if (parameters.get("name") == null)
            this.name = null;
        else {
            this.name = parameters.get("name")[0];
            passedParameterCounter++;

        }
        if (parameters.get("age") == null)
            this.age = null;
        else {
            this.age = parameters.get("age")[0];
            passedParameterCounter++;

        }
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if (username != null)
            map.put("username", username);
        if (password != null)
            map.put("password", password);
        if (name != null)
            map.put("name", name);
        if (age != null)
            map.put("age", age);
        return map;
    }

    public boolean isIdPassed() {
        return id != null;
    }

    public boolean isUsernamePassed() {
        return username != null;
    }

    public boolean isNamePassed() {
        return name != null;
    }

    public boolean isPasswordPassed() {
        return password != null;
    }

    public boolean isAgePassed() {
        return age != null;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPassedParameterCounter() {
        return passedParameterCounter;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}