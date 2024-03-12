package com.mertoatakan.restaurantservice.general;

import com.mertoatakan.restaurantservice.exceptions.RestaurantIdAlreadyExistsException;
import com.mertoatakan.restaurantservice.exceptions.RestaurantNotFoundException;
import com.mertoatakan.restaurantservice.exceptions.RestaurantSaveFailedException;
import com.mertoatakan.restaurantservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    private final KafkaProducerService kafkaProducerService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Map<String, String>> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();

        String description = request.getDescription(false);
        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), errorList.toString(), description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", generalErrorMessages.message());

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(Exception e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(TransactionSystemException e, WebRequest request) {
        String rawMessage = e.getOriginalException().getCause().getMessage();
        String description = request.getDescription(false);

        Pattern pattern = Pattern.compile("messageTemplate='(.*?)'");
        Matcher matcher = pattern.matcher(rawMessage);

        List<String> extractedMessages = new ArrayList<>();
        while (matcher.find()) {
            extractedMessages.add(matcher.group(1));
        }

        String processedMessage = String.join(", ", extractedMessages);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), processedMessage, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", processedMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantNotFoundExceptions(RestaurantNotFoundException e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantIdAlreadyExistsExceptions(RestaurantIdAlreadyExistsException e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantSaveFailedExceptions(RestaurantSaveFailedException e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);

    }

}
