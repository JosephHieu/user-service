package com.josephhieu.springsecurity.mapper;

import com.josephhieu.springsecurity.dto.request.UserCreationRequest;
import com.josephhieu.springsecurity.dto.request.UserUpdateRequest;
import com.josephhieu.springsecurity.dto.response.UserResponse;
import com.josephhieu.springsecurity.entity.User_Entity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User_Entity toUser(UserCreationRequest request);

//    @Mapping(source = "firstName", target = "lastName")
    UserResponse toUserResponse(User_Entity user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User_Entity user, UserUpdateRequest request);
}

