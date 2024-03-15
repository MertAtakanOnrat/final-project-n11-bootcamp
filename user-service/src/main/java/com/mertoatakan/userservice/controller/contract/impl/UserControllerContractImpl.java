package com.mertoatakan.userservice.controller.contract.impl;

import com.mertoatakan.userservice.controller.contract.UserControllerContract;
import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.errormessage.UserErrorMessage;
import com.mertoatakan.userservice.general.BusinessException;
import com.mertoatakan.userservice.mapper.UserMapper;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdatePasswordRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import com.mertoatakan.userservice.request.UserUpdateStatusRequest;
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

    @Override
    public UserDTO getUserById(Long id) {
        User user = userEntityService.findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO getUserByIdAndName(Long id, String name) {
        User user = userEntityService.findByIdAndName(id, name);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userEntityService.findByIdWithControl(id);
        user = UserMapper.INSTANCE.updateUser(request, user);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserStatus(Long id, UserUpdateStatusRequest request) {
        User user = userEntityService.findByIdWithControl(id);
        user.setUserStatus(request.userStatus());
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserPassword(Long id, UserUpdatePasswordRequest request) {
        User user = userEntityService.findByIdWithControl(id);

        if (!user.getPassword().equals(request.oldPass())) {
            throw new BusinessException(UserErrorMessage.INVALID_OLD_PASSWORD);
        }

        if (!request.newPass().equals(request.newPassRepeat())) {
            throw new BusinessException(UserErrorMessage.NEW_PASSWORDS_DID_NOT_MATCH);
        }

        user.setPassword(request.newPass());
        userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userEntityService.deleteUserById(id);
    }
}
