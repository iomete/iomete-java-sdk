package com.iomete.sdk.data.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.data.security.models.DataAccessPolicyRequest;
import com.iomete.sdk.data.security.models.DataAccessPolicyResponse;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class DataAccessPolicyClient implements SdkClient {
    private static final String BASE_PATH = "/api/v1/data-security/access/policy";
    private static final Logger logger = LogManager.getLogger(DataAccessPolicyClient.class);

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public DataAccessPolicyClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        this.objectMapper = new ObjectMapper();
        logger.info("AccessPolicyClient initialized with base URL: " + config.getEndpoint());
    }

    public DataAccessPolicyClient(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    // Method to create access policy
    public DataAccessPolicyResponse createAccessPolicy(DataAccessPolicyRequest request) throws ApiError, IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = restClient.post(BASE_PATH, jsonRequest, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, DataAccessPolicyResponse.class);
    }

    // Update access policy by ID
    public DataAccessPolicyResponse updateAccessPolicy(int policyId, DataAccessPolicyRequest request) throws ApiError, IOException {
        String path = BASE_PATH + "/" + policyId;
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = restClient.put(path, jsonRequest, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, DataAccessPolicyResponse.class);
    }

    // Get access policy by ID
    public DataAccessPolicyResponse getAccessPolicy(int policyId) throws ApiError, IOException {
        String path = BASE_PATH + "/" + policyId;
        String jsonResponse = restClient.get(path, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, DataAccessPolicyResponse.class);
    }

    // Get all access policies
    public List<DataAccessPolicyResponse> getAccessPolicies() throws ApiError, IOException {
        String jsonResponse = restClient.get(BASE_PATH, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, new TypeReference<List<DataAccessPolicyResponse>>() {});
    }

    // Delete access policy by ID
    public void deleteAccessPolicy(int policyId) throws ApiError, IOException {
        String path = BASE_PATH + "/" + policyId;
        restClient.delete(path, defaultHandlerNoContent);
        logger.info("Successfully deleted access policy with ID: " + policyId);
    }

    // Response handler for 201 Created and 200 OK responses, returns the JSON response as a String
    private final ResponseHandler<String> defaultHandlerSuccess = response -> handleResponse(response, 200, 201);

    // Response handler for 204 No Content responses, returns null if successful
    private final ResponseHandler<Void> defaultHandlerNoContent = response -> handleResponse(response, 204);

    // Generalized response handler for multiple expected status codes, returning String for content and Void for no content
    private <T> T handleResponse(HttpResponse response, int... expectedStatuses) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        boolean isExpectedStatus = Arrays.stream(expectedStatuses).anyMatch(code -> code == statusCode);

        if (isExpectedStatus) {
            if (statusCode == 204) {
                // Return null for 204 No Content
                return null;
            } else {
                // Return response content as String for other expected statuses
                try (InputStream content = response.getEntity().getContent()) {
                    return (T) new String(content.readAllBytes(), StandardCharsets.UTF_8);
                }
            }
        } else {
            throw handleError(response);
        }
    }


    // Helper method to handle errors and return ApiError with appropriate message
    private ApiError handleError(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String errorContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            if (errorContent.trim().startsWith("{")) {
                JsonNode errorJson = objectMapper.readTree(errorContent);
                int status = errorJson.has("status") ? errorJson.get("status").asInt() : response.getStatusLine().getStatusCode();
                String errorCode = errorJson.has("errorCode") ? errorJson.get("errorCode").asText() : "UNKNOWN_ERROR";
                String errorMessage = errorJson.has("errorMessage") ? errorJson.get("errorMessage").asText() : "Unknown error";
                return new ApiError(status, errorCode, errorMessage);
            }
            return new ApiError(response.getStatusLine().getStatusCode(), "HTML_RESPONSE_ERROR", errorContent);
        }
        return new ApiError(response.getStatusLine().getStatusCode(), "NO_CONTENT", "No content in response entity.");
    }
}
