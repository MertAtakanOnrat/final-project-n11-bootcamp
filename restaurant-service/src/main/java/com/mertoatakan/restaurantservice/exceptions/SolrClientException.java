package com.mertoatakan.restaurantservice.exceptions;

import com.mertoatakan.restaurantservice.general.BaseErrorMessage;
import com.mertoatakan.restaurantservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SolrClientException extends BusinessException {
    public SolrClientException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
