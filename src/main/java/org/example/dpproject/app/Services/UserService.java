package org.example.dpproject.app.Services;


import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Models.PasswordEncryption;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Repositories.UserRepository;

import javax.crypto.SecretKey;
import java.util.Map;

public class UserService {
    protected UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository("users");
    }

    public Map<String, Object> deleteUser(UserDto dto) {
        return this.userRepository.deleteUser(dto);

    }

    public Map<String, Object> updateUser(UserDto dto) {
        Map<String, Object> user = this.userRepository.getUserById(dto.getId());
        if (user == null) {
            return Map.of("error", "User not found");
        }
        if (dto.getPassword() != null) {
            UserProfile userProfile = new UserProfile(user);
            SecretKey key = PasswordEncryption.reconstructKey(userProfile.getKey());
            try {
                dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
            } catch (Exception e) {
                return Map.of("error", "Error encrypting password");
            }
        }
        return this.userRepository.updateUser(dto);
    }
}
