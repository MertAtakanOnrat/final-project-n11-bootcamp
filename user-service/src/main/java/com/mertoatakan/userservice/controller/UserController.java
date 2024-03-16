package com.mertoatakan.userservice.controller;

import com.mertoatakan.userservice.controller.contract.UserControllerContract;
import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.general.RestResponse;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdatePasswordRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import com.mertoatakan.userservice.request.UserUpdateStatusRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> getUserById(@PathVariable Long id){
        UserDTO userDTO = userControllerContract.getUserById(id);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping("/with-id-and-name")
    public ResponseEntity<RestResponse<UserDTO>> getUserByIdAndName(@RequestParam Long id, @RequestParam String name){
        UserDTO userDTO = userControllerContract.getUserByIdAndName(id, name);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable @NotNull Long id, @Valid @RequestBody UserUpdateRequest request){
        UserDTO userDTO = userControllerContract.updateUser(id, request);
        return ResponseEntity.ok(RestResponse.of(userDTO));

    }

    @PostMapping("/user-status/{id}")
    public ResponseEntity<RestResponse<UserDTO>> updateUserStatus(@PathVariable @NotNull Long id, @Valid @RequestBody UserUpdateStatusRequest request){
        UserDTO userDTO = userControllerContract.updateUserStatus(id, request);
        return ResponseEntity.ok(RestResponse.of(userDTO));

    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<UserDTO>> updateUserPassword(@PathVariable @NotNull Long id, @Valid @RequestBody UserUpdatePasswordRequest request){
        UserDTO userDTO = userControllerContract.updateUserPassword(id, request);
        return ResponseEntity.ok(RestResponse.of(userDTO));

    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@Valid @RequestBody UserSaveRequest request){
        UserDTO userDTO = userControllerContract.saveUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userControllerContract.deleteUserById(id);

    }
}
