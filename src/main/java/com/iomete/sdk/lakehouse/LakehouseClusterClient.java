package com.iomete.sdk.lakehouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.lakehouse.model.LakehouseClusterCreateRequest;
import com.iomete.sdk.lakehouse.model.LakehouseClusterResponse;
import com.iomete.sdk.lakehouse.models.LakehouseClusterResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LakehouseClusterClient implements SdkClient {
    private static final String BASE_PATH = "/api/v1/lakehouses";
    private static final Logger logger = LogManager.getLogger(LakehouseClusterClient.class);

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public LakehouseClusterClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        logger.info("IOMETE LakehouseClusterClient initialized with base URL: " + config.getEndpoint());
    }

    // Method to create a lakehouse cluster
    public LakehouseClusterResponse createLakehouseCluster(LakehouseClusterCreateRequest request) throws ApiError, IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);

        try {
            String jsonResponse = restClient.post(BASE_PATH, jsonRequest, defaultHandler200);
            return objectMapper.readValue(jsonResponse, LakehouseClusterResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to create lakehouse cluster: " + e.getLocalizedMessage());
            throw e;
        }
    }

    // Method to list lakehouse clusters
    public List<LakehouseClusterResponse> listLakehouseClusters() throws ApiError, IOException {

        try {
            String jsonResponse = restClient.get(BASE_PATH, defaultHandler200);

            // Deserialize to the wrapper class
            LakehouseClusterResponseWrapper responseWrapper = objectMapper.readValue(jsonResponse, LakehouseClusterResponseWrapper.class);

            // Return the list of lakehouse clusters
            return responseWrapper.getItems();
        } catch (ApiError | IOException e) {
            logger.error("Failed to list lakehouse clusters: " + e.getLocalizedMessage());
            throw e;
        }
    }

    // Default response handler for 200 OK responses
    private final ResponseHandler<String> defaultHandler200 = response -> {
        if (response.getStatusLine().getStatusCode() == 200) {
            try (InputStream content = response.getEntity().getContent()) {
                return new String(content.readAllBytes(), StandardCharsets.UTF_8);
            }
        } else {

            try (InputStream content = response.getEntity().getContent()) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode errorJson = objectMapper.readTree(content);

                int status = errorJson.has("status") ? errorJson.get("status").asInt() : response.getStatusLine().getStatusCode();
                String errorCode = errorJson.has("errorCode") ? errorJson.get("errorCode").asText() : "UNKNOWN_ERROR";
                String errorMessage = errorJson.has("errorMessage") ? errorJson.get("errorMessage").asText() : "Unknown error";

                throw new ApiError(status, errorCode, errorMessage);
            }
        }
    };
}
