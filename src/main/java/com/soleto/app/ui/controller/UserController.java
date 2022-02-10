package com.soleto.app.ui.controller;

import com.soleto.app.service.UserService;
import com.soleto.app.ui.model.request.UserRequest;
import com.soleto.app.ui.model.response.UserRest;
import com.soleto.app.ui.shared.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public String getUsers() {
        return "get users was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserRequest userRequest) {
        UserRest returnValue = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);

        UserDTO createdUser = service.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }
}
