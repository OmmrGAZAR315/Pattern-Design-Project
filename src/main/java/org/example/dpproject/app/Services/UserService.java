package org.example.dpproject.app.Services;


import org.example.dpproject.app.Http.Responses.HttpResponse;
import org.example.dpproject.app.Repositories.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserService {
    protected UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public boolean deleteUser(int id) {
        Map<String, Object> result = this.userRepository.deleteUser(id);
        return Objects.equals(result.get("status_code"), HttpResponse.OK.getCode());

    }
}
