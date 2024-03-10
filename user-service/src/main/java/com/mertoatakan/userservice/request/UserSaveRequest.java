package com.mertoatakan.userservice.request;

import com.mertoatakan.userservice.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record UserSaveRequest(
                              @NotBlank @Length(min = 1, max = 100) String name,
                              @NotBlank String surname,
                              @NotBlank String email,
                              @NotBlank @Pattern(regexp = "^\\+90\\d{10}$", message = "Invalid phone number") String phoneNumber,
                              @NotNull Double latitude,
                              @NotNull Double longitude,
                              @NotBlank String password,
                              Gender gender) {
}
