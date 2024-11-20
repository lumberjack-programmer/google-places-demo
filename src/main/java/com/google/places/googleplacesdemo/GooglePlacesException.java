package com.google.places.googleplacesdemo;

public class GooglePlacesException extends RuntimeException {
    public GooglePlacesException(String message) {
        super(message);
    }

    public GooglePlacesException(String message, Throwable cause) {
        super(message, cause);
    }
}
