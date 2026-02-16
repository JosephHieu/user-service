package com.josephhieu.springsecurity.controller;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.entity.User_Entity;
import com.josephhieu.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User_Entity createUser(@RequestBody UserCreationRequest request) {

        return userService.createRequest(request);
    }

    @GetMapping
    public List<User_Entity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User_Entity getUser(@PathVariable("userId") String id) {

        return userService.getUser(id);
    }

    @PutMapping("/{userId}")
    public User_Entity updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {

        return userService.updateUser(userId, request);
    }
}
