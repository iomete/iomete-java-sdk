package com.iomete.sdk.spark.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iomete.sdk.client.ResponseHandler;
import com.iomete.sdk.client.RestClient;
import com.iomete.sdk.client.SdkClient;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.models.ApiError;
import com.iomete.sdk.spark.job.models.SparkConfigOverride;
import com.iomete.sdk.spark.job.models.SparkJobCreateInput;
import com.iomete.sdk.spark.job.models.SparkJobResponse;
import com.iomete.sdk.spark.job.models.SparkJobUpdateInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class SparkJobClient implements SdkClient {
    private static final String BASE_PATH = "/api/v1/spark-job";

    private static final Logger logger = LogManager.getLogger(SparkJobClient.class);

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SparkJobClient(SdkClientConfiguration config) {
        this.restClient = new RestClient(config);
        logger.info("IOMETE SparkJobClient initialized with base URL: " + config.getEndpoint());
    }

    public String createJob(SparkJobCreateInput input) throws ApiError, IOException {
        String payload = objectMapper.writeValueAsString(input);

        try {
            return restClient.post(BASE_PATH, payload, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to create job: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String updateJob(String jobId, SparkJobUpdateInput input) throws ApiError, IOException {
        String payload = objectMapper.writeValueAsString(input);
        String url = BASE_PATH + "/" + jobId;

        try {
            return restClient.put(url, payload, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to update job: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public List<SparkJobResponse> getJobs() throws ApiError, IOException {
        try {
            String jsonResponse = restClient.get(BASE_PATH, defaultHandler200);
            return objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, SparkJobResponse.class));
        } catch (ApiError | IOException e) {
            logger.error("Failed to get jobs: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public SparkJobResponse getJobById(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId;

        try {
            String jsonResponse = restClient.get(url, defaultHandler200);
            return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String deleteJobById(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId;

        try {
            return restClient.delete(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to delete job by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String getJobRuns(String jobId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs";

        try {
            return restClient.get(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job runs: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String submitJobRun(String jobId, SparkConfigOverride configOverride) throws ApiError, IOException {
        String payload = objectMapper.writeValueAsString(configOverride);
        String url = BASE_PATH + "/" + jobId + "/runs";

        try {
            return restClient.post(url, payload, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to submit job run: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String cancelJobRun(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId;

        try {
            return restClient.delete(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to cancel job run: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String getJobRunById(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId;

        try {
            return restClient.get(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job run by ID: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String getJobRunLogs(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId + "/logs";

        try {
            return restClient.get(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job run logs: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public String getJobRunMetrics(String jobId, String runId) throws ApiError, IOException {
        String url = BASE_PATH + "/" + jobId + "/runs/" + runId + "/metrics";

        try {
            return restClient.get(url, defaultHandler200);
        } catch (ApiError | IOException e) {
            logger.error("Failed to get job run metrics: " + e.getLocalizedMessage());
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
