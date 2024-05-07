package com.restapi.test.exception;

public class NotFoundExceptionHandle extends RuntimeException {

    public NotFoundExceptionHandle(String message) {
        super(message);
    }
}
