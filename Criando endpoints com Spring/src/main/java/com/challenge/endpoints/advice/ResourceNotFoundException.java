package com.challenge.endpoints.advice;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("Resource: " + message + " not found!");
    }
}
