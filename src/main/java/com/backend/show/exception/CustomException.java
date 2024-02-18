package com.backend.show.exception;

public class CustomException extends RuntimeException {

    public CustomException() {
        super("Custom Exception Thrown");
    }


    public CustomException(RuntimeException e) {
        super(e);
    }

    public CustomException(String msg) {
        super(msg);
    }
}
