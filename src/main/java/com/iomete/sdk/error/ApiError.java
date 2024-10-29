package com.iomete.sdk.error;

public class ApiError extends RuntimeException {
    private final int status;
    private final String errorCode;
    private final String errorMessage;

    public ApiError(int status, String errorCode, String errorMessage) {
        super(errorMessage);  // Pass the errorMessage to RuntimeException constructor
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
