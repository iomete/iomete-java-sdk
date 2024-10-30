package com.iomete.sdk.iam.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.iam.users.models.CreateUserRequest;
import com.iomete.sdk.iam.users.models.CreateUserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class UserClient implements SdkClient {
    private static final Logger logger = LogManager.getLogger(UserClient.class);
    private final RestClient restClient;
    private ObjectMapper objectMapper;

    public UserClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        this.objectMapper = new ObjectMapper();
    }

    // Method to create a new user and return a response with username and temporary password
    public CreateUserResponse createUser(CreateUserRequest request) throws ApiError, IOException {
        String path = "/api/v2/iam/users";
        String jsonRequest = objectMapper.writeValueAsString(request);
        CreateUserResponse jsonResponse = restClient.post(path, jsonRequest, defaultHandlerSuccess);

        if (jsonResponse != null) {
            logger.info("Successfully created user: {}", jsonResponse.getUsername());
            logger.info("Temporary password: {}", jsonResponse.getTemporaryPassword());
            return jsonResponse;
        }
        return null;
    }

    // Method to delete a user by username
    public void deleteUser(String username) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username;
        restClient.delete(path, defaultHandlerNoContent);
        logger.info("Successfully deleted user: " + username);
    }

    // Response handler for 200 OK or 201 Created responses
    private final ResponseHandler<CreateUserResponse> defaultHandlerSuccess = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 201) {
            if (response.getEntity() != null) {
                String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
                return objectMapper.readValue(jsonResponse, CreateUserResponse.class);
            } else {
                throw new ApiError(statusCode, "Empty response body", "Expected response body, but received none.");
            }
        } else {
            throw new ApiError(statusCode, "Unexpected status code", "Received status code: " + statusCode);
        }
    };

    // Response handler for successful 204 No Content responses
    private final ResponseHandler<Void> defaultHandlerNoContent = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 204) {
            return null;  // No content expected in response
        } else {
            throw new ApiError(statusCode, "Unexpected status code", "Received status code: " + statusCode);
        }
    };
}
