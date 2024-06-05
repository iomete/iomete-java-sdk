package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApplicationState {
    private String errorMessage;
    private String state = "PENDING";

    public ApplicationState() {
    }

    public ApplicationState(String errorMessage, String state) {
        this.errorMessage = errorMessage;
        this.state = state;
    }

    // Getters and Setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

