package com.challange.impl.user;

import com.challange.impl.user.model.UserModel;
import com.challange.impl.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {

    private UserService service;

    public List<UserModel> findAll() {
        return service.findAll();
    }

    public UserModel findById(String id) throws Exception {
        return service.findById(id);
    }

    public UserModel create(UserModel user) {
        return service.create(user);
    }
}