package com.mertoatakan.userservice.errormessage;

import com.mertoatakan.userservice.general.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {

    USER_NOT_FOUND("User not found!"),
    INVALID_OLD_PASSWORD("Invalid old password!"),
    NEW_PASSWORDS_DID_NOT_MATCH("New passwords did not match");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
