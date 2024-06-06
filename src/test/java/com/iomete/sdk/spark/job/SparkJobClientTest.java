package com.iomete.sdk.spark.job;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.models.DetailOutputModel;
import com.iomete.sdk.spark.job.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;


public class SparkJobClientTest {
    private final String accessToken = "gqK_vZm9lPUDkRiJ5vSBNzB3FqbGTwY8SouLieZ9eZE";

    SdkClientConfiguration sdkClientConfiguration = new SdkClientConfiguration.Builder()
            .endpoint("https://dev.iomete.cloud")
            .authProvider(new AccessTokenAuthProvider(accessToken))
            .build();

    private final SparkJobClient sparkJobClient = new SparkJobClient(sdkClientConfiguration);

    private static String jobName = "sdk-test-0001";

    @Test
    public void createJobTest() throws IOException {

        var sparkJobCreateRequest = SparkJobCreateRequest.builder()
                .name(jobName)
                .description("This is a test job")
                .schedule("0 0 1 1 *")
                .concurrency(ConcurrencyState.FORBID)
                .applicationConfig(ApplicationConfig.builder()
                        .image("iomete/spark:3.5.1")
                        .mainClass("")
                        .mainApplicationFile("spark-internal")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig.builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("...")
                        .build()
                )
                .resourceTags(Collections.emptyList())
                .build();

        SparkJobResponse response = sparkJobClient.createJob(sparkJobCreateRequest);
        System.out.println(response.toJson());
    }

    // @Test
    // public void createJobFromJson() throws IOException {
    //     SparkJobCreateRequest request = new SparkJobCreateRequest().fromJson("{\n" +
    //             "    \"name\": \"data-compaction\",\n" +
    //             "    \"description\": \"Over time, iceberg tables can slow down and may need data compaction to tidy them up. IOMETE offers a built-in job to execute data " +
    //             "compactions for each table.\",\n" +
    //             "    \"jobType\": \"SCHEDULED\",\n" +
    //             "    \"template\": {\n" +
    //             "        \"isDocker\": true,\n" +
    //             "        \"applicationType\": \"python\",\n" +
    //             "        \"mainApplicationFile\": \"local:///app/driver.py\",\n" +
    //             "        \"configMaps\": [\n" +
    //             "            {\n" +
    //             "                \"key\": \"application.conf\",\n" +
    //             "                \"content\": \"{\\n    expire_snapshot: {\\n        retain_last: 1\\n    },\\n    rewrite_data_files: {\\n        options: {\\n            " +
    //             "\\\"min-input-files\\\": 2,\\n            \\\"target-file-size-bytes\\\": 536870912,\\n        } \\n    },\\n    rewrite_manifests: {\\n        use_caching: " +
    //             "true\\n    }\\n}\",\n" +
    //             "                \"mountPath\": \"/etc/configs/application.conf\"\n" +
    //             "            }\n" +
    //             "        ],\n" +
    //             "        \"deps\": {},\n" +
    //             "        \"instanceConfig\": {\n" +
    //             "            \"driverType\": \"driver-x-small\",\n" +
    //             "            \"executorType\": \"exec-x-small\",\n" +
    //             "            \"executorCount\": 1\n" +
    //             "        },\n" +
    //             "        \"imagePullSecrets\": [\n" +
    //             "            \"default\"\n" +
    //             "        ],\n" +
    //             "        \"image\": \"iomete/iomete_data_compaction:1.0.0\",\n" +
    //             "        \"volumeId\": \"ba8a321b-f78c-45b6-b805-bc64bc07b7e1\"\n" +
    //             "    },\n" +
    //             "    \"schedule\": \"0 12 * * 0\",\n" +
    //             "    \"concurrency\": \"FORBID\"\n" +
    //             "}"
    //     );
    // }

    // @Test
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

    @Test
    public void getJobById() throws IOException {
        String jobId = "2ee1ee68-039b-4269-9aac-34b4f4d0cbf4";
        DetailOutputModel<SparkJobResponse> response = sparkJobClient.getJobById(jobId);
        System.out.println(response.getItem().toJson());
    }

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
