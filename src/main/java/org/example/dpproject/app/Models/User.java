package org.example.dpproject.app.Models;

import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.DB.Relation;
import org.example.dpproject.app.Helpers.HelperClass;

import java.util.Base64;
import java.util.List;
import java.util.Map;

public class User extends Model {
    public static final String table = "users";
    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private byte[] key;

    public User() {
    }

    public User(String username, String password, String name, int age, byte[] key) throws Exception {
        this.username = username;
        this.password = password;
        this.key = key;
        this.name = name;
        this.age = age;
    }

    public Post[] posts() {
        return this.hasMany(Post.class, "user_id", id);
    }

    public User(Map<String, Object> userData) {
        if (userData.get("id") == null)
            this.id = 0;
        else
            this.id = (int) userData.get("id");
        if (userData.get("username") == null)
            this.username = "null";
        else
            this.username = (String) userData.get("username");

        if (userData.get("password") == null)
            this.password = "null";
        else
            this.password = (String) userData.get("password");

        if (userData.get("name") == null)
            this.name = "null";
        else
            this.name = (String) userData.get("name");

        if (userData.get("age") == null)
            this.age = 0;
        else
            this.age = (int) userData.get("age");

        if (userData.get("secretKey") == null)
            this.key = null;
        else
            this.key = (byte[]) userData.get("secretKey");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getKey() {
        return key;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public String getKeyToString() {
        return Base64.getEncoder().encodeToString(key);
    }
}
