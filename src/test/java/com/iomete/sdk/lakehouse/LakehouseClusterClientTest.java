package com.iomete.sdk.lakehouse;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.lakehouse.model.LakehouseClusterCreateRequest;
import com.iomete.sdk.lakehouse.model.LakehouseClusterResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LakehouseClusterClientTest {
    private final String dataPlaneEndpoint = "https://dev.iomete.cloud";
    private final String accessToken = "access-token";

    private final LakehouseClusterClient lakehouseClusterClient = new LakehouseClusterClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );

    @Test
    public void listLakehouseClusters() throws IOException {
        // Call the method to get the list of lakehouse clusters
        List<LakehouseClusterResponse> response = lakehouseClusterClient.listLakehouseClusters();

        // Assert that the response is not null and contains at least one lakehouse cluster
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(0);

        // Print the response for debugging purposes
        System.out.println(response);
    }

    @Test
    public void createLakehouseClusterTest() throws IOException {
        // Build a request for creating a lakehouse cluster
        LakehouseClusterCreateRequest request = new LakehouseClusterCreateRequest();
        request.setName("test-cluster");
        request.setNamespace("default");
        request.setType("X_SMALL");

        // Set node types
        LakehouseClusterCreateRequest.NodeTypes nodeTypes = new LakehouseClusterCreateRequest.NodeTypes();
        nodeTypes.setDriver("driver-x-small");
        nodeTypes.setExecutor("exec-x-small");
        request.setNodeTypes(nodeTypes);

        // Set auto-scale configuration
        LakehouseClusterCreateRequest.AutoScale autoScale = new LakehouseClusterCreateRequest.AutoScale();
        autoScale.setEnabled(true);
        autoScale.setIdleTimeoutInSeconds(1800);
        request.setAutoScale(autoScale);

        // Create the lakehouse cluster and assert the response
        LakehouseClusterResponse response = lakehouseClusterClient.createLakehouseCluster(request);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("test-cluster");

        // Print the response for debugging
        System.out.println("Created Lakehouse Cluster: " + response.getDriverErrorMessage());

    }

    @Test
    public void getLakehouseClusterWithInvalidEndpoint() {
        // Create a client with an invalid endpoint
        LakehouseClusterClient invalidClient = new LakehouseClusterClient(
                new SdkClientConfiguration
                        .Builder()
                        .endpoint("https://invalid-endpoint")
                        .authProvider(new AccessTokenAuthProvider(accessToken))
                        .build()
        );

        // Expect an ApiError when calling the method with the invalid client
        ApiError exception = assertThrows(ApiError.class, () -> {
            invalidClient.listLakehouseClusters();
        });

        // Assert that the status code is 404 or another error code indicating failure
        assertThat(exception.getErrorCode()).isEqualTo(404); // Or use another relevant code
    }
}
