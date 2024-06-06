package com.iomete.sdk;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.spark.job.SparkJobClient;
import com.iomete.sdk.spark.job.models.SparkConfigOverride;
import com.iomete.sdk.spark.job.models.SparkRunResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoadTest {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    private int count = 0;
    private final int maxCount;

    private final String accessToken = "gqK_vZm9lPUDkRiJ5vSBNzB3FqbGTwY8SouLieZ9eZE";

    SdkClientConfiguration sdkClientConfiguration = new SdkClientConfiguration.Builder()
            .endpoint("https://dev.iomete.cloud")
            .authProvider(new AccessTokenAuthProvider(accessToken))
            .build();

    private final SparkJobClient sparkJobClient = new SparkJobClient(sdkClientConfiguration);

    public LoadTest(int maxCount) {
        this.maxCount = maxCount;
    }

    public void start() {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    callFunction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                count++;
                if (count >= maxCount) {
                    scheduler.shutdown();
                }
            }
        };

        int sleepSeconds = 2;
        final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(task, 0, sleepSeconds, TimeUnit.SECONDS);
    }

    public void callFunction() throws IOException {
        String catalogSyncJobId = "2ee1ee68-039b-4269-9aac-34b4f4d0cbf4";

        SparkRunResponse response = sparkJobClient.submitJobRun(catalogSyncJobId, SparkConfigOverride
                .builder()
                .envVars(Map.of("TRIGGERED_FROM_SDK", "true"))
                .build()
        );

        System.out.println("Function called at: " + System.currentTimeMillis());
        System.out.print(" with name: " + response.getName());
    }

    public static void main(String[] args) {
        int x = 500; // Number of times to call the function
        new LoadTest(x).start();
    }
}
