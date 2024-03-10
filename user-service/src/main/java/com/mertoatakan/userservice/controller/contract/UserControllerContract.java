package com.mertoatakan.userservice.controller.contract;

import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.request.UserSaveRequest;

import java.util.List;

public interface UserControllerContract {

    UserDTO saveUser(UserSaveRequest request);
    List<UserDTO> getAllUsers();
}
