package com.iomete.sdk.iam.roles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.iam.roles.models.CreateRoleRequest;
import com.iomete.sdk.error.ApiError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
public class RoleClient  implements SdkClient {
        private static final Logger logger = LogManager.getLogger(RoleClient.class);
        private final RestClient restClient;
        private final ObjectMapper objectMapper;

        public RoleClient(SdkClientConfiguration config) {
            this.restClient = new RestClient(config);
            this.objectMapper = new ObjectMapper();
        }

    // Method to create a new role and handle 200/201 responses
    public void createRole(CreateRoleRequest request) throws ApiError, IOException {
        String path = "/api/v2/iam/roles";
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = restClient.post(path, jsonRequest, defaultHandlerSuccess);
        logger.info("Successfully created role: " + request.getName());

        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            logger.info("Response from server: " + jsonResponse);
        }
    }

    // Method to delete a role by name
    public void deleteRole(String roleName) throws ApiError, IOException {
        String path = "/api/v2/iam/roles/" + roleName;
        restClient.delete(path, defaultHandlerNoContent);
        logger.info("Successfully deleted role: " + roleName);
    }

    // Response handler for 200 OK or 201 Created responses
    private final ResponseHandler<String> defaultHandlerSuccess = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 201) {
            return response.getEntity() != null ? new String(response.getEntity().getContent().readAllBytes()) : null;
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
