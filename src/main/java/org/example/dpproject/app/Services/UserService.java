package org.example.dpproject.app.Services;


import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.Http.DTOs.UserDto;
import org.example.dpproject.app.Models.PasswordEncryption;
import org.example.dpproject.app.Models.UserProfile;
import org.example.dpproject.app.Repositories.UserRepository;

import javax.crypto.SecretKey;
import java.util.Objects;

public class UserService {
    protected UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository("users");
    }

    public QBResults deleteUser(UserDto dto) {
        return this.userRepository.deleteUser(dto);
    }

    public QBResults updateUser(UserDto dto) {
        QBResults result = this.userRepository.getUserBy("id", dto.getId());
        if (result.getStatusCode() != HttpResponse.OK.getCode())
            return result.setCustom_message("id not found");

        if (dto.getPassword() != null) {
            UserProfile userProfile = new UserProfile(result.first());
            SecretKey key = PasswordEncryption.reconstructKey(userProfile.getKey());
            try {
                dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
            } catch (Exception e) {
                return result.setCustom_message("error encrypting password");
            }
        }
        return this.userRepository.updateUser(dto);
    }

    public QBResults login(UserDto dto) {
        QBResults results = this.userRepository.pluck("secretKey").getUserBy("username", dto.getUsername());
        if (results.getStatusCode() != HttpResponse.OK.getCode())
            return results.setCustom_message("username not found");

        UserProfile userProfile = new UserProfile(results.first());
        SecretKey key = PasswordEncryption.reconstructKey(userProfile.getKey());
        try {
            dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
        } catch (Exception e) {
            return results.setCustom_message("error encrypting password");
        }
        results = this.userRepository.login(dto);
        if (results.getStatusCode() != HttpResponse.OK.getCode())
            return results.setCustom_message("invalid username and password");

        userProfile = new UserProfile(results.first());
        boolean authenticated = false;
        try {
            authenticated = Objects.equals(userProfile.getPassword(), PasswordEncryption.encrypt(dto.getPassword(), key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (authenticated) {
            return results;
        } else
            return results.setMessage(HttpResponse.UNAUTHORIZED);
    }
}
