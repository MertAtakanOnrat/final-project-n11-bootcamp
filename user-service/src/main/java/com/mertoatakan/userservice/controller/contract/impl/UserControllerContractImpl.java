package com.mertoatakan.userservice.controller.contract.impl;

import com.mertoatakan.userservice.controller.contract.UserControllerContract;
import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.mapper.UserMapper;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;
    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userEntityService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }
}
