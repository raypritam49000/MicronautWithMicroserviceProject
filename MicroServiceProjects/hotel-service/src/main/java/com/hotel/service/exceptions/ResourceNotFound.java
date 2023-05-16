package com.hotel.service.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super("Data Note Found");
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
