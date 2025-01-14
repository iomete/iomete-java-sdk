package com.iomete.sdk.client;

import com.iomete.sdk.auth.AuthProvider;


public class SdkClientConfiguration {
    private static final int DEFAULT_CONN_TIMEOUT = 10_000;

    private final String endpoint;
    private final String domain;
    private final AuthProvider authProvider;
    private final int timeoutMs;

    private SdkClientConfiguration(Builder builder) {
        this.endpoint = builder.endpoint;
        this.domain = builder.domain;
        this.authProvider = builder.authProvider;
        this.timeoutMs = builder.timeoutMs;
    }

    public static class Builder {
        private String endpoint;
        private String domain;
        private AuthProvider authProvider;
        private int timeoutMs = DEFAULT_CONN_TIMEOUT;

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder authProvider(AuthProvider authProvider) {
            this.authProvider = authProvider;
            return this;
        }

        public Builder timeoutMs(int timeout) {
            this.timeoutMs = timeout;
            return this;
        }

        public SdkClientConfiguration build() {
            return new SdkClientConfiguration(this);
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getDomain() {
        return domain;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public int getTimeoutMs() {
        return timeoutMs;
    }
}

