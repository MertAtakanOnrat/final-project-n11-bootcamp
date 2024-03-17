package com.mertoatakan.userservice.mapper;

import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.dto.UserLocationDTO;
import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "password", target = "password")
    @Mapping(target = "userStatus", constant = "ACTIVE")
    User convertToUser(UserSaveRequest request);

    UserDTO convertToUserDTO(User user);

    UserLocationDTO convertToUserLocationDTO(User user);

    List<UserDTO> convertToUserDTOs(List<User> users);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    User updateUser(UserUpdateRequest request, @MappingTarget User user);

}
