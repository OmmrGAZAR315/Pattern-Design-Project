package org.example.dpproject.app.Services;


import org.example.dpproject.app.Repositories.UserRepository;

import java.util.List;
import java.util.Map;

public class UserService {
    protected UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void deleteUser(int id) {
        Map<String, Object> result = this.userRepository.deleteUser(id);
        if (result.get("status_code") == HttpResonse.SUCCESS.getValue()) {
            System.out.println("User not found");
        } else {
            System.out.println("User deleted");
        }

    }
}
