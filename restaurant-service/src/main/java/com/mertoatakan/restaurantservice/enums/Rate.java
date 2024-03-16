package com.mertoatakan.restaurantservice.enums;

public enum Rate {
    VERY_POOR(1),
    POOR(2),
    AVERAGE(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    Rate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rate fromValue(int value) {
        for (Rate rate : values()) {
            if (rate.getValue() == value) {
                return rate;
            }
        }
        throw new IllegalArgumentException("Invalid rate value: " + value);
    }
}
