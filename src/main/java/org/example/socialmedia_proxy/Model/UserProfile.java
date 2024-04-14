package org.example.socialmedia_proxy.Model;

import org.example.socialmedia_proxy.PasswordEncryption;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class UserProfile {
    private String username;
    private String password;
    private String name;
    private int age;
    private byte[] key;

    public UserProfile(String username, String password, String name, int age) throws Exception {
        this.username = username;
        SecretKey key = PasswordEncryption.generateKey();
        this.password = PasswordEncryption.encrypt(password, key);
        this.key = key.getEncoded();
        this.name = name;
        this.age = age;
    }

    public UserProfile(Map<String, Object> userData) {
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
}
