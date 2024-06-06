package com.iomete.sdk.spark.job;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.spark.job.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;


public class SparkJobClientTest {
    private final String accessToken = "gqK_vZm9lPUDkRiJ5vSBNzB3FqbGTwY8SouLieZ9eZE";

    SdkClientConfiguration config = new SdkClientConfiguration
            .Builder()
            .endpoint("https://dev.iomete.cloud")
            .authProvider(new AccessTokenAuthProvider(accessToken))
            .build();

    private final SparkJobClient sparkJobClient = new SparkJobClient(config);

    @Test
    public void createJobTest() throws IOException {
        String random = String.valueOf(System.currentTimeMillis());

        SparkJobCreateRequest input = new SparkJobCreateRequest();
        input.setName("sdk-test-" + random);
        // input.setSchedule("0 0 1 1 *");
        SparkConfig config = new SparkConfig();
        config.setImage("iomete/spark:3.5.1");
        config.setMainClass("");
        config.setMainApplicationFile("spark-internal");
        config.setApplicationType(ApplicationType.JVM);
        config.setArguments(Collections.emptyList());
        config.setEnvVars(Collections.emptyMap());
        // config.setJavaOptions("");
        config.setSparkConf(Collections.emptyMap());
        // config.setDeps(null);
        config.setConfigMaps(Collections.emptyList());
        config.setInstanceConfig(new InstanceConfig("driver-x-small", "exec-x-small"));
        // config.setVolumeId(null);

        input.setSparkConfig(config);

        SparkJobResponse response = sparkJobClient.createJob(input);
        System.out.println(response.toJson());
    }

    // public SparkJobResponse updateJob(String jobId, SparkJobUpdateInput input) throws ApiError, IOException {
    //     String jsonRequest = objectMapper.writeValueAsString(input);
    //     String url = BASE_PATH + "/" + jobId;
    //
    //     try {
    //         String jsonResponse = restClient.put(url, jsonRequest, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to update job: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public ListOutputModel<SparkJobResponse> getJobs() throws ApiError, IOException {
    //     try {
    //         String jsonResponse = restClient.get(BASE_PATH, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, new TypeReference<>() {});
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get jobs: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public DetailOutputModel<SparkJobResponse> getJobById(String jobId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId;
    //
    //     try {
    //         String jsonResponse = restClient.get(url, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, new TypeReference<>() {});
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get job by ID: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public SparkJobResponse deleteJobById(String jobId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId;
    //
    //     try {
    //         String jsonResponse = restClient.delete(url, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, SparkJobResponse.class);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to delete job by ID: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public List<RunOutput> getJobRuns(String jobId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId + "/runs";
    //
    //     try {
    //         String jsonResponse = restClient.get(url, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, RunOutput.class));
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get job runs: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public RunOutput submitJobRun(String jobId, SparkConfigOverride configOverride) throws ApiError, IOException {
    //     String payload = objectMapper.writeValueAsString(configOverride);
    //     String url = BASE_PATH + "/" + jobId + "/runs";
    //
    //     try {
    //         String jsonResponse = restClient.post(url, payload, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, RunOutput.class);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to submit job run: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public RunOutput cancelJobRun(String jobId, String runId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId + "/runs/" + runId;
    //
    //     try {
    //         String jsonResponse = restClient.delete(url, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, RunOutput.class);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to cancel job run: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public RunOutput getJobRunById(String jobId, String runId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId + "/runs/" + runId;
    //
    //     try {
    //         String jsonResponse = restClient.get(url, defaultHandler200);
    //         return objectMapper.readValue(jsonResponse, RunOutput.class);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get job run by ID: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }

    // public String getJobRunLogs(String jobId, String runId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId + "/runs/" + runId + "/logs";
    //
    //     try {
    //         return restClient.get(url, defaultHandler200);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get job run logs: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
    //
    // public String getJobRunMetrics(String jobId, String runId) throws ApiError, IOException {
    //     String url = BASE_PATH + "/" + jobId + "/runs/" + runId + "/metrics";
    //
    //     try {
    //         return restClient.get(url, defaultHandler200);
    //     } catch (ApiError | IOException e) {
    //         logger.error("Failed to get job run metrics: " + e.getLocalizedMessage());
    //         throw e;
    //     }
    // }
}
