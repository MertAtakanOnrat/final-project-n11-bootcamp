package com.mertoatakan.restaurantservice.exceptions;

import com.mertoatakan.restaurantservice.general.BaseErrorMessage;
import com.mertoatakan.restaurantservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestaurantIdAlreadyExistsException extends BusinessException {
    public RestaurantIdAlreadyExistsException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
