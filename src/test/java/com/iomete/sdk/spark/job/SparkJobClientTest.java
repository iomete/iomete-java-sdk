package com.iomete.sdk.spark.job;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.spark.job.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class SparkJobClientTest {
    private final String accessToken = "gqK_vZm9lPUDkRiJ5vSBNzB3FqbGTwY8SouLieZ9eZE";

    SdkClientConfiguration sdkClientConfiguration = new SdkClientConfiguration.Builder()
            .endpoint("https://dev.iomete.cloud")
            .authProvider(new AccessTokenAuthProvider(accessToken))
            .build();

    private final SparkJobClient sparkJobClient = new SparkJobClient(sdkClientConfiguration);

    private final static String jobName = "sdk-test-0001";

    // This could be deleted
    private final static String dataCompactionJobId = "798963b6-7158-4769-bb53-b8063a1310e2";

    // Do not delete this
    private final static String catalogSyncJobId = "2ee1ee68-039b-4269-9aac-34b4f4d0cbf4";

    @Test
    public void createJobTest() throws IOException {

        var sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .description("This is a test job")
                .schedule("0 0 1 1 *")
                .concurrency(ConcurrencyState.FORBID)
                .applicationConfig(ApplicationConfig
                        .builder()
                        .image("iomete/spark:3.5.1")
                        .mainClass("")
                        .mainApplicationFile("spark-internal")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
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

    @Test
    public void createJobFromJson() throws IOException {
        SparkJobCreateRequest sparkJobCreateRequest = new SparkJobCreateRequest().fromJson("""
                {
                    "name": "data-compaction",
                    "description": "Over time, iceberg tables can slow down and may need data compaction to tidy them up. IOMETE offers a built-in job to execute data compactions for each table.",
                    "jobType": "SCHEDULED",
                    "template": {
                        "isDocker": true,
                        "applicationType": "python",
                        "mainApplicationFile": "local:///app/driver.py",
                        "configMaps": [
                            {
                                "key": "application.conf",
                                "content": "{\\n    expire_snapshot: {\\n        retain_last: 1\\n    },\\n    rewrite_data_files: {\\n        options: {\\n            \\"min-input-files\\": 2,\\n            \\"target-file-size-bytes\\": 536870912,\\n        } \\n    },\\n    rewrite_manifests: {\\n        use_caching: true\\n    }\\n}",
                                "mountPath": "/etc/configs/application.conf"
                            }
                        ],
                        "deps": {},
                        "instanceConfig": {
                            "driverType": "driver-x-small",
                            "executorType": "exec-x-small",
                            "executorCount": 1
                        },
                        "imagePullSecrets": [
                            "default"
                        ],
                        "image": "iomete/iomete_data_compaction:1.0.0",
                        "volumeId": "ba8a321b-f78c-45b6-b805-bc64bc07b7e1"
                    },
                    "schedule": "0 12 * * 0",
                    "concurrency": "FORBID"
                }"""
        );

        SparkJobResponse response = sparkJobClient.createJob(sparkJobCreateRequest);
        System.out.println(response.toJson());
    }

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

    @Test
    public void getJobs() throws IOException {
        List<SparkJobResponse> response = sparkJobClient.getJobs();
        System.out.println(response);
    }


    @Test
    public void getJobById() throws IOException {
        SparkJobResponse response = sparkJobClient.getJobById(dataCompactionJobId);
        System.out.println(response.toJson());
    }

    @Test
    public void deleteJobById() throws IOException {
        var job = sparkJobClient.deleteJobById(dataCompactionJobId);
        System.out.println(job.toJson());
    }

    @Test
    public void getJobRuns() throws IOException {
        var runs = sparkJobClient.getJobRuns(catalogSyncJobId);
        runs.forEach(run -> System.out.println(run.toJson()));
    }

    @Test
    public void submitJobRun() throws IOException {
        SparkRunResponse response = sparkJobClient.submitJobRun(catalogSyncJobId, SparkConfigOverride
                .builder()
                .envVars(Map.of("ENV_FROM_SDK", "SDK_VALUE"))
                .build()
        );

        System.out.println(response.toJson());
    }

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

    @Test
    public void getJobRunById() throws IOException {
        // submit run then get details
        SparkRunResponse submittedRun = sparkJobClient.submitJobRun(catalogSyncJobId);

        SparkRunResponse response = sparkJobClient.getJobRunById(catalogSyncJobId, submittedRun.getId());
        System.out.println(response.toJson());
    }

}
