package com.mertoatakan.userservice.request;

import javax.validation.constraints.NotBlank;

public record UserUpdatePasswordRequest(@NotBlank String oldPass,
                                        @NotBlank String newPass,
                                        @NotBlank String newPassRepeat) {
}
