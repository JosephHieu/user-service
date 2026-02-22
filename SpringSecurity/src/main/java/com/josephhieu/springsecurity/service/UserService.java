package com.josephhieu.springsecurity.service;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.dto.response.UserResponse;
import com.josephhieu.springsecurity.entity.User_Entity;
import com.josephhieu.springsecurity.exception.AppException;
import com.josephhieu.springsecurity.exception.ErrorCode;
import com.josephhieu.springsecurity.mapper.UserMapper;
import com.josephhieu.springsecurity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public User_Entity createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User_Entity user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User_Entity> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {

        User_Entity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
