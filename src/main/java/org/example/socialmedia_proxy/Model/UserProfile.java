package org.example.socialmedia_proxy.Model;

public class UserProfile {
    private String username;
    private String password;
    private String name;
    private int age;
    private String email;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
//        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
