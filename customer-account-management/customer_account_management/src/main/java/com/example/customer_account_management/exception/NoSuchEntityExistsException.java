package com.example.customer_account_management.exception;

public class NoSuchEntityExistsException extends RuntimeException {
    private String message;

    public NoSuchEntityExistsException() {
    }

    public NoSuchEntityExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
