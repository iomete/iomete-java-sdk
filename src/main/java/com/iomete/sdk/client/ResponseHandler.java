package com.iomete.sdk.client;

import com.iomete.sdk.models.ApiError;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

@FunctionalInterface
public interface ResponseHandler<T> {
    T handleResponse(CloseableHttpResponse response) throws IOException, ApiError;
}
