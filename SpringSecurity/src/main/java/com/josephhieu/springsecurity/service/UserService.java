package com.josephhieu.springsecurity.service;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.entity.User_Entity;
import com.josephhieu.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User_Entity createRequest(UserCreationRequest request) {

        User_Entity userEntity = User_Entity.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .build();

        return userRepository.save(userEntity);
    }

    public List<User_Entity> getUsers() {
        return userRepository.findAll();
    }

    public User_Entity getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found User"));
    }

    public User_Entity updateUser(String userId, UserUpdateRequest request) {

        User_Entity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not Found User"));

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }
}
