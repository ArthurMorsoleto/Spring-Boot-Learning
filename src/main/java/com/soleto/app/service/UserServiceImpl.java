package com.soleto.app.service;

import com.soleto.app.io.entity.UserEntity;
import com.soleto.app.io.repository.UserRepository;
import com.soleto.app.ui.shared.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    @ExceptionHandler
    public UserDTO createUser(UserDTO user) {
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity userEntity = createUserEntity(user);
        UserEntity newStoredUser = repository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(newStoredUser, returnValue);

        return returnValue;
    }

    private UserEntity createUserEntity(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("testEncryptedPassword");
        userEntity.setUserId(UUID.randomUUID().toString());
        return userEntity;
    }
}
