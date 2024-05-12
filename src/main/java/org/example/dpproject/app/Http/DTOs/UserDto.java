package org.example.dpproject.app.Http.DTOs;

import java.util.HashMap;
import java.util.Map;

public class UserDto {
    private String id;
    private final String username;
    private String password;
    private final String name;
    private final String age;

    public UserDto(Map<String, String[]> parameters) {
        if (parameters.get("id") == null)
            this.id = null;
        else
            this.id = parameters.get("id")[0];
        if (parameters.get("username") == null)
            this.username = null;
        else
            this.username = parameters.get("username")[0];
        if (parameters.get("password") == null)
            this.password = null;
        else
            this.password = parameters.get("password")[0];
        if (parameters.get("name") == null)
            this.name = null;
        else
            this.name = parameters.get("name")[0];
        if (parameters.get("age") == null)
            this.age = null;
        else
            this.age = parameters.get("age")[0];
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

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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