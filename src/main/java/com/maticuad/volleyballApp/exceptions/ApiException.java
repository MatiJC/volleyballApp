package com.maticuad.volleyballApp.exceptions;

public class ApiException extends Exception {
    public ApiException() {
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message) {
        super(message);
    }
}
