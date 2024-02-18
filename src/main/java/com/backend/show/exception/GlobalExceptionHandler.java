package com.backend.show.exception;

import com.backend.show.model.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e){
        var response =
                ExceptionResponse
                        .builder()
                        .msg(e.getMessage())
                        .httpStatus(BAD_REQUEST)
                        .build();
        LOGGER.error("Error Occured : ",e);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
}
