package com.mertoatakan.userservice.exceptions;

import com.mertoatakan.userservice.general.BaseErrorMessage;
import com.mertoatakan.userservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends BusinessException {
    public RestaurantNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
