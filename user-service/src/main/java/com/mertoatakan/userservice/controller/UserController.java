package com.mertoatakan.userservice.controller;

import com.mertoatakan.userservice.controller.contract.UserControllerContract;
import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.general.RestResponse;
import com.mertoatakan.userservice.request.UserSaveRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Validated
@Tag(name = "User Controller", description = "User Management")
public class UserController {

    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> getAllUsers(){
        List<UserDTO> allUsers = userControllerContract.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(allUsers));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@Valid @RequestBody UserSaveRequest request){
        UserDTO userDTO = userControllerContract.saveUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }
}
