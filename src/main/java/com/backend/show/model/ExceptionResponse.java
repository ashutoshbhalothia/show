package com.backend.show.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    @JsonIgnore
    private Exception exception;
    private String msg;
    private HttpStatus httpStatus;
}
