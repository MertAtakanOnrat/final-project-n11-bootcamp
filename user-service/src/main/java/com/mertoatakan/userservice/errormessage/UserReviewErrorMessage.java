package com.mertoatakan.userservice.errormessage;

import com.mertoatakan.userservice.general.BaseErrorMessage;

public enum UserReviewErrorMessage implements BaseErrorMessage {
    USER_REVIEW_NOT_FOUND("User review not found!");
    private final String message;

    UserReviewErrorMessage(String message) {
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
