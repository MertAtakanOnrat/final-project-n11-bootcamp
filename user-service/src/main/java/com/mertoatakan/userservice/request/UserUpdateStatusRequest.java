package com.mertoatakan.userservice.request;

import com.mertoatakan.userservice.enums.UserStatus;

import javax.validation.constraints.NotBlank;

public record UserUpdateStatusRequest(@NotBlank(message = "User Status should not be blank!") UserStatus userStatus) {
}
