package com.josephhieu.springsecurity.mapper;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.dto.response.UserResponse;
import com.josephhieu.springsecurity.entity.User_Entity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User_Entity toUser(UserCreationRequest request);

//    @Mapping(source = "firstName", target = "lastName")
    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User_Entity user);

    void updateUser(@MappingTarget User_Entity user, UserUpdateRequest request);
}
