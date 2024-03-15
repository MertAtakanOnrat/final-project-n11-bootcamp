package com.mertoatakan.restaurantservice.general;

import com.mertoatakan.restaurantservice.exceptions.RestaurantIdAlreadyExistsException;
import com.mertoatakan.restaurantservice.exceptions.RestaurantNotFoundException;
import com.mertoatakan.restaurantservice.exceptions.RestaurantSaveFailedException;
import com.mertoatakan.restaurantservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    private final KafkaProducerService kafkaProducerService;

    private static String errorLog = "errorLog";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String originalMessage = ex.getMostSpecificCause().getMessage();
        String importantPartOfMessage = extractImportantPartOfMessage(originalMessage);

        String conciseError = "Invalid request body: " + importantPartOfMessage;

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), conciseError, request.getDescription(false));
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", conciseError);

        return new ResponseEntity<>(restResponse, headers, HttpStatus.BAD_REQUEST);
    }

    private String extractImportantPartOfMessage(String errorMessage) {

        int endIndex = errorMessage.indexOf("Enum class") + "Enum class".length();
        if (endIndex != -1 && errorMessage.length() > endIndex) {
            String importantPart = errorMessage.substring(0, endIndex);

            int startEnumValuesIndex = errorMessage.indexOf("[", endIndex);
            int endEnumValuesIndex = errorMessage.indexOf("]", startEnumValuesIndex) + 1;
            if (startEnumValuesIndex != -1 && endEnumValuesIndex != -1) {
                importantPart += errorMessage.substring(startEnumValuesIndex, Math.min(endEnumValuesIndex + 1, errorMessage.length()));
            }
            return importantPart.length() > 255 ? importantPart.substring(0, 252) + "..." : importantPart;
        }

        return errorMessage.length() > 255 ? errorMessage.substring(0, 252) + "..." : errorMessage;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Map<String, String>> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        String description = request.getDescription(false);
        String formattedErrorList = formatErrorList(errorList);
        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), formattedErrorList, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", generalErrorMessages.message());

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    private String formatErrorList(List<Map<String, String>> errorList) {
        return errorList.stream()
                .map(errorMap -> errorMap.entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("; "));
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(Exception e, WebRequest request){

        String message = e.getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(errorLog, message);

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

        String message = e.getBaseErrorMessage().getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(errorLog, message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantIdAlreadyExistsExceptions(RestaurantIdAlreadyExistsException e, WebRequest request){

        String message = e.getBaseErrorMessage().getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(errorLog, message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantSaveFailedExceptions(RestaurantSaveFailedException e, WebRequest request){

        String message = e.getBaseErrorMessage().getMessage();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(errorLog, message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);

    }

}
