package com.mertoatakan.userservice.controller.contract;

import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdatePasswordRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import com.mertoatakan.userservice.request.UserUpdateStatusRequest;

import java.util.List;

public interface UserControllerContract {

    UserDTO saveUser(UserSaveRequest request);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByIdAndName(Long id, String name);
    UserDTO updateUser(Long id, UserUpdateRequest request);
    UserDTO updateUserStatus(Long id, UserUpdateStatusRequest request);
    UserDTO updateUserPassword(Long id, UserUpdatePasswordRequest request);
    void deleteUserById(Long id);
}
