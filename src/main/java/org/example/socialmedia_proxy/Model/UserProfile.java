package org.example.socialmedia_proxy.Model;

import java.util.Map;

public class UserProfile {
    private String username;
    private String password;
    private String name;
    private int age;

    public UserProfile(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public UserProfile(Map<String, Object> userData) {
        if (userData == null)
            return;
        if (userData.get("username") == null)
            this.username = "null";
        else this.username = (String) userData.get("username");
        if (userData.get("password") == null)
            this.password = "null";
        else this.password = (String) userData.get("password");
        if (userData.get("name") == null)
            this.name = "null";
        else this.name = (String) userData.get("name");
        if (userData.get("age") == null)
            this.age = 0;
        else this.age = (int) userData.get("age");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
