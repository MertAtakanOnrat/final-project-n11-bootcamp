package com.mertoatakan.userservice.general;

public enum GeneralErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("Item not found!"),
    ITEM_SAVE_FAILED("Item save failed!");

    private final String message;

    GeneralErrorMessage(String message) {
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
