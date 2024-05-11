package org.example.dpproject.app.Services;


import org.example.dpproject.app.DAOs.UserDao;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Http.Responses.HttpResponse;
import org.example.dpproject.app.Models.PasswordEncryption;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Repositories.UserRepository;

import javax.crypto.SecretKey;
import java.util.Map;
import java.util.Objects;

public class UserService {
    protected UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository("users");
    }

    public boolean deleteUser(UserDto dto) {
        Map<String, Object> result = this.userRepository.deleteUser(dto);
        return Objects.equals(result.get("status_code"), HttpResponse.OK.getCode());

    }

    public boolean updateUser(UserDto dto) {
        Map<String, Object> user = this.userRepository.getUserById(dto.getId());
        if (user == null) {
            return false;
        }
        UserProfile userProfile = new UserProfile(user);
        SecretKey key = PasswordEncryption.reconstructKey(userProfile.getKey());
        try {
            dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
        } catch (Exception e) {
            return false;
        }
        Map<String, Object> result = this.userRepository.updateUser(dto);
        return Objects.equals(result.get("status_code"), HttpResponse.OK.getCode());
    }
}
