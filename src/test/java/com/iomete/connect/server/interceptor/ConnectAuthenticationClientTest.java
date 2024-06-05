package com.iomete.connect.server.interceptor;

import com.iomete.sdk.ConnectAuthenticationClient;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ConnectAuthenticationClientTest {
    private ConnectAuthenticationClient authClient;
    private CloseableHttpClient mockHttpClient;

    @BeforeEach
    public void setUp() {
        mockHttpClient = Mockito.mock(CloseableHttpClient.class);
        // Assuming HttpClientFactory is appropriately mocked to return mockHttpClient
        authClient = new ConnectAuthenticationClient(mockHttpClient);
    }

    @Test
    public void testSuccessfulAuthentication() throws IOException {
        // Mocking a successful response from the server
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
        when(mockHttpClient.execute(any(HttpPost.class))).thenReturn(mockResponse);

        authClient.authenticate("testCluster", "testUser", "testToken");

        // Verify cache update
        assertThat(authClient.getCache()).containsKey("testClustertestUsertestToken");
    }

    @Test
    public void testUnauthenticatedError() throws IOException {
        // Mocking a 401 response
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 401, "Unauthorized"));
        when(mockResponse.getEntity()).thenReturn(new StringEntity("{\"message\":\"Invalid token!\"}"));
        when(mockHttpClient.execute(any(HttpPost.class))).thenReturn(mockResponse);

        assertThatThrownBy(() -> authClient.authenticate("testCluster", "testUser", "testToken"))
                .isInstanceOf(ConnectAuthenticationClient.AuthenticationApiError.class)
                .hasMessage("Invalid token!");
    }

    @Test
    public void testClientFailWithUnexpectedError() throws IOException {
        CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 503, "Unexpected Error! Something went wrong!"));
        when(mockHttpClient.execute(any(HttpPost.class))).thenReturn(mockResponse);
        when(mockHttpClient.execute(any(HttpPost.class))).thenReturn(mockResponse);

        assertThatThrownBy(() -> authClient.authenticate("testCluster", "testUser", "testToken"))
                .isInstanceOf(ConnectAuthenticationClient.AuthenticationApiError.class)
                .hasMessage("Authentication failed");
    }
}
