# IOMETE Java SDK

Deploy and monitor Spark applications with the IOMETE SDK for Java. 
The SDK makes it easy to call IOMETE services using REST APIs. 

## Getting Started

### Authentication

SDK uses API Access Token to authenticate requests. Authentication sent in the header (`x-api-token`) of each request.
You can obtain API Access Token from IOMETE Console - [View documentation](https://iomete.com/resources/user-guide/create-a-personal-access-token).

### Clients

Currently, SDK only supports:  
- Spark Job Client

### Usage

Define the IOMETE client and set the API Access Token.

```java
private final String dataPlaneEndpoint = "http(s)://example.com";
private final String accessToken = "your_api_token";

private final SparkJobClient sparkJobClient = new SparkJobClient(
        new SdkClientConfiguration
                .Builder()
                .endpoint(dataPlaneEndpoint)
                .authProvider(new AccessTokenAuthProvider(accessToken))
                .build()
);
```


To create a new Spark Job, use the following code (Replace the values with your own)  
> **_TIP:_** In practise Spark Jobs could be created from UI Console and SDK should be used to trigger the job and monitor the Run status.

```java
String jobName = "sdk-job-001";

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
```

### Trigger and Monitor Spark Job

In example below, we are submitting a Spark Job Run and using periodic check to monitoring its status.  
If the job run is not completed within the threshold time, we are trying to cancel the run and throw an exception.  

Take a note that we can override some parameters, like arguments, env, javaOpts, etc. during submitting (triggering) Spark Job.  

```java
SparkRunResponse runResponse = sparkJobClient.submitJobRun(
        temporarySampleJob.getId(),
        SparkConfigOverride
                .builder()
                .arguments(List.of("arg1", "arg2"))
                .envVars(Map.of(
                        "SDK_ENV_VAR_1", "value1",
                        "SDK_ENV_VAR_2", "value2"
                ))
                .resourceTags(List.of(
                        new ResourceTag("source", "sdk"),
                        new ResourceTag("env", "dev")
                ))
                // Add more overrides as needed
                .build()
);

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
```
