package org.example.dpproject.app.Http.DTOs;

public class UserDto {
    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private byte[] key;

    public UserDto setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }
}
