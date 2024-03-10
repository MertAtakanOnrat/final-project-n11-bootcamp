package com.mertoatakan.userservice.general;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(Exception e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        String description = request.getDescription(false);
        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), "Validation Failed", description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }


}
