package com.restapi.test.exception;

public class AlreadyExistsExceptionHandle extends RuntimeException {

    public AlreadyExistsExceptionHandle(String message) {
        super(message);
    }
}
