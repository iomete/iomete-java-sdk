package com.iomete.sdk.auth;

public interface AuthProvider {
    String getAuthorizationHeader();
    String getAuthorizationToken();
}
