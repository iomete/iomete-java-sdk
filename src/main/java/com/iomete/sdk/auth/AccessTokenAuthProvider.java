package com.iomete.sdk.auth;

public class AccessTokenAuthProvider implements AuthProvider {
    private final String apiKey;

    public AccessTokenAuthProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getAuthorizationHeader() {
        return "x-api-token";
    }

    @Override
    public String getAuthorizationToken() {
        return apiKey;
    }
}

