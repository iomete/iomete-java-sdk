package com.iomete.connect.server.interceptor;

import com.iomete.sdk.ConnectAuthenticationClient;
import org.junit.jupiter.api.Tag;
import com.iomete.sdk.ConnectAuthenticationClient.AuthenticationApiError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Tag("integration")
class ConnectAuthenticationClientIntegrationTest {
    private final ConnectAuthenticationClient service = new ConnectAuthenticationClient();

    @Test
    void testInvalidUserIdThrowError() {
        assertThatThrownBy(() -> service.authenticate(
                "cluster1",
                "admin",
                "invalid-token")
        ).isInstanceOf(AuthenticationApiError.class)
        .hasMessageContaining("Invalid token");
    }
}
