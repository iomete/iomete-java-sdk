package com.iomete.sdk.spark.job;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.spark.job.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SparkJobClientTest {
    private final String dataPlaneEndpoint = "http(s)://example.com";
    private final String accessToken = "api_token";

    private final SparkJobClient sparkJobClient = new SparkJobClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );

    @Test
    public void createJob() throws IOException {
        String jobName = "catalog-sync-sdk-001";

        var sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .template(ApplicationTemplate
                        .builder()
                        .image("iomete/iom-catalog-sync:1.10.0")
                        .mainClass("com.iomete.catalogsync.App")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("59cdccbd-975b-41c8-b232-18e73fad577f")
                        .build()
                )
                .build();

        SparkJobResponse response = sparkJobClient.createJob(sparkJobCreateRequest);
        System.out.println(response.toJson());

        // Clean up
        sparkJobClient.deleteJobById(response.getId());
    }

    @Test
    public void createJobFromJSONTemplate() throws IOException {
        String jobName = "catalog-sync-sdk-002";

        var sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .template(new ApplicationTemplate().fromJson("""
                        {
                          "isDocker": true,
                          "image": "iomete/iom-catalog-sync:1.10.0",
                          "imagePullSecrets": [],
                          "mainClass": "com.iomete.catalogsync.App",
                          "mainApplicationFile": "spark-internal",
                          "applicationType": "jvm",
                          "envVars": {},
                          "deps": {},
                          "instanceConfig": {
                            "driverType": "driver-x-small",
                            "executorType": "exec-x-small",
                            "executorCount": 1
                          },
                          "volumeId": "59cdccbd-975b-41c8-b232-18e73fad577f"
                        }
                    """)
                )
                .build();

        SparkJobResponse response = sparkJobClient.createJob(sparkJobCreateRequest);
        System.out.println(response.toJson());

        // Clean up
        sparkJobClient.deleteJobById(response.getId());
    }

    @Test
    public void getJobs() throws IOException {
        List<SparkJobResponse> response = sparkJobClient.getJobs();
        System.out.println(response.toString());
    }

    @Test
    public void getJobByNonExistingId() {
        ApiError exception = assertThrows(ApiError.class, () -> {
            sparkJobClient.getJobById("non-existing-id");
        });

        // Assert that the status code is 404
        assertThat(exception.getStatusCode()).isEqualTo(404);
    }

    @Test
    public void getJobRuns() throws IOException {
        // No need to create and delete job in production. Job could be created from the UI Console.
        // This is just a sample test that will create temporary job and delete it after the test.
        SparkJobResponse temporarySampleJob = createSampleJob();
        var runs = sparkJobClient.getJobRuns(temporarySampleJob.getId());

        assertThat(runs.size()).isEqualTo(0);

        // Clean up
        sparkJobClient.deleteJobById(temporarySampleJob.getId());
    }

    @Test
    public void triggerJobAndWaitForCompletion() throws IOException {
        // No need to create and delete job in production. Job could be created from the UI Console.
        // This is just a sample test that will create temporary job and delete it after the test.
        SparkJobResponse temporarySampleJob = createSampleJob();

        SparkRunResponse runResponse = sparkJobClient.submitJobRun(temporarySampleJob.getId());

        // Periodically get run details and check status, with threshold for waiting time.
        // If waiting time threshold is exceeded, try to cancel the run and throw an exception.

        long thresholdMinutes = 5; // Adjust threshold as needed
        Instant startTime = Instant.now();
        boolean isCompleted = false;

        while (Duration.between(startTime, Instant.now()).toMinutes() < thresholdMinutes) {
            System.out.println("Checking job run status for " + runResponse.getName());

            SparkRunResponse runDetails = sparkJobClient.getJobRunById(temporarySampleJob.getId(), runResponse.getId());
            SparkRunStatus status = runDetails.getDriverStatus();

            System.out.println("Status is: " + runDetails.getDriverStatus());
            System.out.println("Duration: " + Duration.between(startTime, Instant.now()).toMinutes() + " minutes");

            if (status == SparkRunStatus.COMPLETED) {
                isCompleted = true;
                break;
            } else if (status == SparkRunStatus.FAILED || status == SparkRunStatus.ABORTED) {
                throw new RuntimeException("Job run failed or aborted.");
            }

            try {
                Thread.sleep(10000); // Sleep for 10 seconds before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        if (!isCompleted) {
            sparkJobClient.cancelJobRun(temporarySampleJob.getId(), runResponse.getId());
            throw new RuntimeException("Job run did not complete within the threshold time.");
        }

        // Clean up, if completed, otherwise leave it for debugging
        sparkJobClient.deleteJobById(temporarySampleJob.getId());
    }

    @Test
    public void triggerJobWithOverridesAndWaitForCompletion() throws IOException {
        // No need to create and delete job in production. Job could be created from the UI Console.
        // This is just a sample test that will create temporary job and delete it after the test.
        SparkJobResponse temporarySampleJob = createSampleJob();

        SparkRunResponse runResponse = sparkJobClient.submitJobRun(
                temporarySampleJob.getId(),
                SparkConfigOverride
                        .builder()
                        .arguments(List.of("arg1", "arg2"))
                        .envVars(Map.of(
                                "SDK_ENV_VAR_1", "value1",
                                "SDK_ENV_VAR_2", "value2"
                        ))
                        // Add more overrides as needed
                        .build()
        );

        Assertions.assertThat(runResponse.getConfig().getArguments()).containsExactly("arg1", "arg2");
        Assertions.assertThat(runResponse.getConfig().getEnvVars()).containsEntry("SDK_ENV_VAR_1", "value1");
        Assertions.assertThat(runResponse.getConfig().getEnvVars()).containsEntry("SDK_ENV_VAR_2", "value2");

        // Periodically get run details and check status, with threshold for waiting time.
        // If waiting time threshold is exceeded, try to cancel the run and throw an exception.

        long thresholdMinutes = 5; // Adjust threshold as needed
        Instant startTime = Instant.now();
        boolean isCompleted = false;

        while (Duration.between(startTime, Instant.now()).toMinutes() < thresholdMinutes) {
            System.out.println("Checking job run status for " + runResponse.getName());

            SparkRunResponse runDetails = sparkJobClient.getJobRunById(temporarySampleJob.getId(), runResponse.getId());
            SparkRunStatus status = runDetails.getDriverStatus();

            System.out.println("Status is: " + runDetails.getDriverStatus());
            System.out.println("Duration: " + Duration.between(startTime, Instant.now()).toMinutes() + " minutes");

            if (status == SparkRunStatus.COMPLETED) {
                isCompleted = true;
                break;
            } else if (status == SparkRunStatus.FAILED || status == SparkRunStatus.ABORTED) {
                throw new RuntimeException("Job run failed or aborted.");
            }

            try {
                Thread.sleep(10000); // Sleep for 10 seconds before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        if (!isCompleted) {
            sparkJobClient.cancelJobRun(temporarySampleJob.getId(), runResponse.getId());
            throw new RuntimeException("Job run did not complete within the threshold time.");
        }

        // Clean up, if completed, otherwise leave it for debugging
        sparkJobClient.deleteJobById(temporarySampleJob.getId());
    }

    private SparkJobResponse createSampleJob() throws IOException {
        String jobName = "catalog-sync-sdk-sample";

        var sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .template(ApplicationTemplate
                        .builder()
                        .image("iomete/iom-catalog-sync:1.10.0")
                        .mainClass("com.iomete.catalogsync.App")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("59cdccbd-975b-41c8-b232-18e73fad577f")
                        .build()
                )
                .build();

        return sparkJobClient.createJob(sparkJobCreateRequest);
    }
}
