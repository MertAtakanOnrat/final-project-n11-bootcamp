package com.mertoatakan.userservice.dto;

import com.mertoatakan.userservice.enums.Gender;
import com.mertoatakan.userservice.enums.UserStatus;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      String email,
                      String phoneNumber,
                      Double latitude,
                      Double longitude,
                      Gender gender,
                      UserStatus userStatus) {
}
