package com.iomete.hive;

import com.iomete.IometeHiveAuthenticatorService;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IometeHiveAuthenticatorServiceTest {
    private IometeHiveAuthenticatorService authenticatorService;
    private CloseableHttpClient mockHttpClient;

    @BeforeEach
    void setUp() {
        mockHttpClient = Mockito.mock(CloseableHttpClient.class);
        authenticatorService = new IometeHiveAuthenticatorService(mockHttpClient, "http://fake-auth-service", "testLakehouse");
    }

    @Test
    void testSuccessfulAuthentication() throws IOException {
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
        when(mockHttpClient.execute(any(HttpGet.class))).thenReturn(mockResponse);

        authenticatorService.Authenticate("user1", "pass");
    }

    @Test
    void testUnauthenticatedError() throws IOException {
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 401, "Unauthorized"));
        when(mockResponse.getEntity()).thenReturn(new StringEntity("{\"message\":\"Unauthorized\"}"));
        when(mockHttpClient.execute(any(HttpGet.class))).thenReturn(mockResponse);

        assertThatThrownBy(() -> authenticatorService.Authenticate("user", "pass"))
                .isInstanceOf(AuthenticationException.class)
                .hasMessageContaining("Unauthorized");
    }

    @Test
    void testClientFailWithUnexpectedError() throws IOException {
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 500, "Internal Server Error"));
        when(mockHttpClient.execute(any(HttpGet.class))).thenReturn(mockResponse);

        assertThatThrownBy(() -> authenticatorService.Authenticate("user", "pass"))
                .isInstanceOf(AuthenticationException.class)
                .hasMessageContaining("Unexpected response code");
    }
}
