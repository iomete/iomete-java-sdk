package com.iomete.sdk.client;

import com.iomete.sdk.error.ApiError;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    private final CloseableHttpClient httpClient;
    private final SdkClientConfiguration config;

    public RestClient(SdkClientConfiguration config) {
        this.httpClient = HttpClientFactory.getInstance().createHttpClient();
        this.config = config;
    }

    public <T> T get(String urlPath, ResponseHandler<T> responseHandler) throws ApiError, IOException {
        HttpGet request = new HttpGet(config.getEndpoint() + urlPath);

        return executeRequest(request, urlPath, responseHandler);
    }

    public <T> T post(String urlPath, String jsonPayload, ResponseHandler<T> responseHandler) throws ApiError, IOException {
        HttpPost request = new HttpPost(config.getEndpoint() + urlPath);
        request.setEntity(new StringEntity(jsonPayload));

        return executeRequest(request, urlPath, responseHandler);
    }

    public <T> T put(String urlPath, String jsonPayload, ResponseHandler<T> responseHandler) throws ApiError, IOException {
        HttpPut request = new HttpPut(config.getEndpoint() + urlPath);
        request.setEntity(new StringEntity(jsonPayload));

        return executeRequest(request, urlPath, responseHandler);
    }

    public <T> T delete(String urlPath, ResponseHandler<T> responseHandler) throws ApiError, IOException {
        HttpDelete request = new HttpDelete(config.getEndpoint() + urlPath);

        return executeRequest(request, urlPath, responseHandler);
    }

    private <T> T executeRequest(HttpUriRequest request, String url, ResponseHandler<T> responseHandler) throws ApiError, IOException {
        request.addHeader("Content-Type", "application/json");
        request.addHeader(config.getAuthProvider().getAuthorizationHeader(), config.getAuthProvider().getAuthorizationToken());

        long startTime = System.nanoTime();
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            logger.debug("Request duration: " + duration + " ms. Endpoint: " + url);

            return responseHandler.handleResponse(response);
        }
    }
}
