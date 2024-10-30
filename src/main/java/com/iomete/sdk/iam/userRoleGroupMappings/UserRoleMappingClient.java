
package com.iomete.sdk.iam.userRoleGroupMappings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserInfoResponse;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserRoleResponse;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserGroupResponse;
import com.iomete.sdk.error.ApiError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UserRoleMappingClient implements SdkClient {
    private static final Logger logger = LogManager.getLogger(UserRoleMappingClient.class);
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public UserRoleMappingClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        this.objectMapper = new ObjectMapper();
    }

    // To fetch user info with username
    public UserInfoResponse getUser(String username) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username;
        String jsonResponse = restClient.get(path, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, UserInfoResponse.class);
    }

    //   To fetch roles associated to user with this username
    public List<UserRoleResponse> getUserRoles(String username) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/roles";
        String jsonResponse = restClient.get(path, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, new TypeReference<List<UserRoleResponse>>() {});
    }

    //  To fetch groups user with this username
    public List<UserGroupResponse> getUserGroups(String username) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/groups";
        String jsonResponse = restClient.get(path, defaultHandlerSuccess);
        return objectMapper.readValue(jsonResponse, new TypeReference<List<UserGroupResponse>>() {});
    }
    // To add groups to a user with this username
    public void addGroups(String username, List<String> groupNames) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/groups";
        String jsonRequest = objectMapper.writeValueAsString(groupNames);
        restClient.post(path, jsonRequest, defaultHandlerNoContent);
        logger.info("Successfully added groups to user: " + username);
    }

    // To remove groups to a user with this username
    public void removeGroups(String username, List<String> groupNames) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/groups";
        String jsonRequest = objectMapper.writeValueAsString(groupNames);
        restClient.delete(path, jsonRequest, defaultHandlerNoContent);
        logger.info("Successfully removed groups from user: " + username);
    }
    // Method to add roles to a user
    public void addRoles(String username, List<String> roleNames) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/roles";
        String jsonRequest = objectMapper.writeValueAsString(roleNames);
        restClient.post(path, jsonRequest, defaultHandlerNoContent);
        logger.info("Successfully added roles to user: " + username);
    }

    // Method to remove roles from a user
    public void removeRoles(String username, List<String> roleNames) throws ApiError, IOException {
        String path = "/api/v2/iam/users/" + username + "/roles";
        String jsonRequest = objectMapper.writeValueAsString(roleNames);
        restClient.delete(path, jsonRequest, defaultHandlerNoContent);
        logger.info("Successfully removed roles from user: " + username);
    }


    // Response handler for successful 204 No Content responses
    private final ResponseHandler<Void> defaultHandlerNoContent = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 204) {
            return null;  // No content expected in response
        } else {
            throw new ApiError(statusCode, "Unexpected status code", "Received status code: " + statusCode);
        }
    };

    // Response handler for successful 200-201 responses
    private final ResponseHandler<String> defaultHandlerSuccess = response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            return new String(response.getEntity().getContent().readAllBytes());
        } else {
            throw new ApiError(statusCode, "Unexpected status code", "Received status code: " + statusCode);
        }
    };
}
