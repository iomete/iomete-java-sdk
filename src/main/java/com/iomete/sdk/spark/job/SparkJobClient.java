package com.iomete.sdk.spark.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iomete.sdk.client.*;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.models.DetailOutputModel;
import com.iomete.sdk.models.ListOutputModel;
import com.iomete.sdk.spark.job.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class SparkJobClient implements SdkClient {
    private static final String BASE_PATH = "/api/v1/spark-jobs";

    private static final Logger logger = LogManager.getLogger(SparkJobClient.class);

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public SparkJobClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        logger.info("IOMETE SparkJobClient initialized with base URL: " + config.getEndpoint());
    }

    public SparkJobResponse createJob(SparkJobCreateRequest input) throws ApiError, IOException {
        String jsonRequest = objectMapper.writeValueAsString(input);

        try {
            String jsonResponse = restClient.post(BASE_PATH, jsonRequest, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to create job: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkJobResponse updateJob(String jobId, SparkJobUpdateRequest input) throws ApiError, IOException {
        String jsonRequest = objectMapper.writeValueAsString(input);
        String url = BASE_PATH + "/" + jobId;

        try {
            String jsonResponse = restClient.put(url, jsonRequest, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to update job: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public ListOutputModel<SparkJobResponse> getJobs() throws ApiError, IOException {
        try {
            String jsonResponse = restClient.get(BASE_PATH, defaultHandler200);
            return objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        } catch (ApiError | IOException e) {
            logger.error("Failed to get jobs: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public DetailOutputModel<SparkJobResponse> getJobById(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId;

        try {
            String jsonResponse = restClient.get(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkJobResponse deleteJobById(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId;

        try {
            String jsonResponse = restClient.delete(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to delete job by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public List<SparkRunResponse> getJobRuns(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs";

        try {
            String jsonResponse = restClient.get(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, SparkRunResponse.class));
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job runs: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkRunResponse submitJobRun(String jobId, SparkConfigOverride configOverride) throws ApiError, IOException {
        String payload = objectMapper.writeValueAsString(configOverride);
        String url = BASE_PATH + "/" + jobId + "/runs";

        try {
            String jsonResponse = restClient.post(url, payload, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkRunResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to submit job run: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkRunResponse cancelJobRun(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId;

        try {
            String jsonResponse = restClient.delete(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkRunResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to cancel job run: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkRunResponse getJobRunById(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId;

        try {
            String jsonResponse = restClient.get(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkRunResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job run by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    protected ResponseHandler<String> defaultHandler200 = response -> {
        if (response.getStatusLine().getStatusCode() == 200) {
            try (InputStream content = response.getEntity().getContent()) {
                return new String(content.readAllBytes(), StandardCharsets.UTF_8);
            }
        } else {
            throw new ApiError(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
        }
    };
}
