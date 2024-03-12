package com.mertoatakan.restaurantservice.errormessage;

import com.mertoatakan.restaurantservice.general.BaseErrorMessage;

public enum RestaurantErrorMessage implements BaseErrorMessage {
    RESTAURANT_NOT_FOUND("Restaurant not found!"),
    RESTAURANT_SAVE_FAILED("Restaurant save failed!"),
    RESTAURANT_ID_ALREADY_EXISTS("Restaurant id already exists!");


    private final String message;

    RestaurantErrorMessage(String message) {
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
