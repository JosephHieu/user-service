package com.josephhieu.springsecurity.controller;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.dto.response.ApiResponse;
import com.josephhieu.springsecurity.entity.User_Entity;
import com.josephhieu.springsecurity.service.UserService;
import jakarta.validation.Valid;
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
    public ApiResponse<User_Entity> createUser(@RequestBody @Valid UserCreationRequest request) {

        return ApiResponse.<User_Entity>builder()
                .result(userService.createRequest(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<User_Entity>> getUsers() {
        return ApiResponse.<List<User_Entity>>builder()
                .result(userService.getUsers())
                .message("Lấy danh sách người dùng thành công")
                .build();
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
