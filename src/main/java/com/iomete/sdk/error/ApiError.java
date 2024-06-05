package com.iomete.sdk.error;

public class ApiError extends RuntimeException {
    private final int statusCode;

    public ApiError(int statusCode, String message) {
        super(message); // Pass the message to the RuntimeException constructor
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", message=" + getLocalizedMessage() +
                '}';
    }
}
