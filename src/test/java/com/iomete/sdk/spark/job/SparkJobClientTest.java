package com.iomete.sdk.spark.job;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.models.ResourceTag;
import com.iomete.sdk.spark.job.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SparkJobClientTest {
    private final String dataPlaneEndpoint = "https://dev.iomete.cloud";
    private final String accessToken = "access-token";
    private final String namespace = "namespace";
    private final String domain = "domain";
    private final String user = "user";

    private final SparkJobClient sparkJobClient = new SparkJobClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .domain(domain)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );

    @Test
    public void createPriorityJob() throws IOException {
        String jobName = "catalog-sync-priority-sdk-001";

        SparkJobCreateRequest sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .namespace(namespace)
                .bundleId("3fe16353-ddb0-443c-b3f6-8dbfe3914afe")
                .jobType(SparkJobType.MANUAL)
                .jobUser(user)
                .flow(FlowType.PRIORITY)
                .priority(Priority.HIGH)
                .template(ApplicationTemplate
                        .builder()
                        .image("iomete/iom-catalog-sync:5.0.0")
                        .mainClass("com.iomete.catalogsync.App")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("920a140e-fc90-481a-8ec2-4e395bfa6450")
                        .build()
                )
                .build();

        SparkJobResponse response = sparkJobClient.createJob(sparkJobCreateRequest);
        System.out.println(response.toJson());

//         Clean up
        sparkJobClient.deleteJobById(response.getId());
    }

    @Test
    public void createJob() throws IOException {
        String jobName = "catalog-sync-sdk-001";

        SparkJobCreateRequest sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .namespace(namespace)
                .bundleId("3fe16353-ddb0-443c-b3f6-8dbfe3914afe")
                .jobType(SparkJobType.MANUAL)
                .jobUser(user)
                .template(ApplicationTemplate
                        .builder()
                        .image("iomete/iom-catalog-sync:5.0.0")
                        .mainClass("com.iomete.catalogsync.App")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("920a140e-fc90-481a-8ec2-4e395bfa6450")
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

        SparkJobCreateRequest sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .namespace(namespace)
                .bundleId("3fe16353-ddb0-443c-b3f6-8dbfe3914afe")
                .jobType(SparkJobType.MANUAL)
                .jobUser(user)
                .template(new ApplicationTemplate().fromJson(
                    "{\n" +
                    "  \"isDocker\": true,\n" +
                    "  \"image\": \"iomete/iom-catalog-sync:3.0.0\",\n" +
                    "  \"imagePullSecrets\": [],\n" +
                    "  \"mainClass\": \"com.iomete.catalogsync.App\",\n" +
                    "  \"mainApplicationFile\": \"spark-internal\",\n" +
                    "  \"applicationType\": \"jvm\",\n" +
                    "  \"envVars\": {},\n" +
                    "  \"deps\": {},\n" +
                    "  \"instanceConfig\": {\n" +
                    "    \"driverType\": \"driver-x-small\",\n" +
                    "    \"executorType\": \"exec-x-small\",\n" +
                    "    \"executorCount\": 1\n" +
                    "  },\n" +
                    "  \"volumeId\": \"920a140e-fc90-481a-8ec2-4e395bfa6450\"\n" +
                    "}"
                ))
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
    public void getJobByName() throws IOException {
        SparkJobResponse temporarySampleJob = createSampleJob();

        SparkJobResponse response = sparkJobClient.getJobByName(temporarySampleJob.getName());
        assertThat(response.getName()).isEqualTo(temporarySampleJob.getName());

        // Clean up
        sparkJobClient.deleteJobById(temporarySampleJob.getId());
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
        List<SparkRunResponse> runs = sparkJobClient.getJobRuns(temporarySampleJob.getId());

        assertThat(runs.size()).isEqualTo(0);

        // Clean up
        sparkJobClient.deleteJobById(temporarySampleJob.getId());
    }

    @Test
    public void triggerJobAndWaitForCompletion() throws IOException, InterruptedException {
        // No need to create and delete job in production. Job could be created from the UI Console.
        // This is just a sample test that will create temporary job and delete it after the test.
        SparkJobResponse temporarySampleJob = createSampleJob();

        SparkRunResponse runResponse = sparkJobClient.submitJobRun(temporarySampleJob.getId());

        // Periodically get run details and check status, with threshold for waiting time.
        // If waiting time threshold is exceeded, try to cancel the run and throw an exception.

        long thresholdMinutes = 5; // Adjust threshold as needed
        Instant startTime = Instant.now();
        boolean isCompleted = false;
        Thread.sleep(10000);

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
    public void triggerJobWithOverridesAndWaitForCompletion() throws IOException, InterruptedException {
        // No need to create and delete job in production. Job could be created from the UI Console.
        // This is just a sample test that will create temporary job and delete it after the test.
        SparkJobResponse temporarySampleJob = createSampleJob();

        SparkRunResponse runResponse = sparkJobClient.submitJobRun(
                temporarySampleJob.getId(),
                SparkConfigOverride
                        .builder()
                        .arguments(Arrays.asList("arg1", "arg2")).arguments(Arrays.asList("arg1", "arg2"))
                        .envVars(new HashMap<String, String>() {{
                            put("SDK_ENV_VAR_1", "value1");
                            put("SDK_ENV_VAR_2", "value2");
                        }})
                        .resourceTags(Arrays.asList(
                                new ResourceTag("source", "sdk"),
                                new ResourceTag("env", "dev")
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
        Thread.sleep(10000);

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

        SparkJobCreateRequest sparkJobCreateRequest = SparkJobCreateRequest
                .builder()
                .name(jobName)
                .namespace(namespace)
                .bundleId("3fe16353-ddb0-443c-b3f6-8dbfe3914afe")
                .jobType(SparkJobType.MANUAL)
                .jobUser(user)
                .template(ApplicationTemplate
                        .builder()
                        .image("iomete/iom-catalog-sync:5.0.0")
                        .mainClass("com.iomete.catalogsync.App")
                        .applicationType(ApplicationType.JVM)
                        .instanceConfig(InstanceConfig
                                .builder()
                                .driverType("driver-x-small")
                                .executorType("exec-x-small")
                                .build()
                        )
                        .volumeId("32bccf34-1404-40f5-86d9-9e3c566181a5")
                        .build()
                )
                .build();

        return sparkJobClient.createJob(sparkJobCreateRequest);
    }
}
