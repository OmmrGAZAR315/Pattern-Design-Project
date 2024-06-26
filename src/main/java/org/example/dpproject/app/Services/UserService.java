package org.example.dpproject.app.Services;


import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Helpers.HttpResponse;
import org.example.dpproject.app.DTOs.UserDto;
import org.example.dpproject.app.Models.PasswordEncryption;
import org.example.dpproject.app.Models.User;
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
            User user = new User(result.first());
            SecretKey key = PasswordEncryption.reconstructKey(user.getKey());
            try {
                dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
            } catch (Exception e) {
                return result.setCustom_message("error encrypting password");
            }
        }
        return this.userRepository.updateUser(dto);
    }

    public QBResults login(UserDto dto) {
        QBResults results = this.userRepository
                .getUserBy("username", dto.getUsername());
        if (results != null) {
            if (results.getStatusCode() == HttpResponse.INTERNAL_SERVER_ERROR.getCode())
                return results.setCustom_message("No operations allowed after connection closed.");
            if (results.getStatusCode() != HttpResponse.OK.getCode())
                return results.setCustom_message("username not found");

            User user = new User(results.first());
            SecretKey key = PasswordEncryption.reconstructKey(user.getKey());
            try {
                dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
            } catch (Exception e) {
                return results.setCustom_message("error encrypting password");
            }
//        System.out.println(dto.getPassword());
            if (!Objects.equals(dto.getPassword(), user.getPassword()))
                results.setCustom_message("invalid username and password");
        } else
            return new QBResults();
        return results;
    }

    public QBResults signUp(UserDto dto) {
        byte[] keyBytes;
        try {

            SecretKey key = PasswordEncryption.generateKey();
            dto.setPassword(PasswordEncryption.encrypt(dto.getPassword(), key));
            keyBytes = key.getEncoded();
        } catch (Exception e) {
            return new QBResults().setCustom_message("error encrypting password");
        }

        QBResults results = userRepository.signUp(dto, keyBytes);
        if (results.getStatusCode() != HttpResponse.CREATED.getCode())
            return results.setCustom_message("error creating user");

        int userId = (int) results.first().get("id");
        return userRepository.getUserBy("id", userId);
    }
}
