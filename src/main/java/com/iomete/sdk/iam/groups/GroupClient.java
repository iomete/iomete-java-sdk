package com.iomete.sdk.iam.groups;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.iam.groups.models.CreateGroupRequest;
import com.iomete.sdk.iam.groups.models.CreateGroupResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GroupClient implements SdkClient {
    private static final Logger logger = LogManager.getLogger(GroupClient.class);
    private final RestClient restClient;
    private ObjectMapper objectMapper;

    public GroupClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        this.objectMapper = new ObjectMapper();
    }

    // Method to create a new group
    public CreateGroupResponse createGroup(CreateGroupRequest request) throws ApiError, IOException {
        String path = "/api/v2/iam/groups";
        String jsonRequest = objectMapper.writeValueAsString(request);
        CreateGroupResponse createGroupResponse = restClient.post(path, jsonRequest, defaultHandlerSuccess);
        logger.info("Successfully created group: " + createGroupResponse.getName());
        return createGroupResponse;
    }

    // Method to delete a group by name
    public void deleteGroup(String groupName) throws ApiError, IOException {
        String path = "/api/v2/iam/groups/" + groupName;
        restClient.delete(path, defaultHandlerNoContent);
        logger.info("Successfully deleted group: " + groupName);
    }

    // Response handler for 200 OK or 201 Created responses with content
    private final ResponseHandler<CreateGroupResponse> defaultHandlerSuccess = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 201) {
            if (response.getEntity() != null) {
                String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
                return objectMapper.readValue(jsonResponse, CreateGroupResponse.class);
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
