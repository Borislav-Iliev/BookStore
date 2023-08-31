package com.example.bookstore.model.exception;

public class ObjectNotFoundException extends RuntimeException {

    private String message;

    public ObjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ObjectNotFoundException setMessage(String message) {
        this.message = message;
        return this;
    }
}
