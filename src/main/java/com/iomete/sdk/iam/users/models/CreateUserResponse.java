package com.iomete.sdk.iam.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {
    private String username;
    private String temporaryPassword;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }
}
