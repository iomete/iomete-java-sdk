package com.iomete.sdk.data.security;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.error.ApiError;
import com.iomete.sdk.data.security.models.DataAccessPolicyRequest;
import com.iomete.sdk.data.security.models.DataAccessPolicyResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAccessPolicyClientTest {

        private final String dataPlaneEndpoint = "https://dev.iomete.cloud";
        private final String accessToken = "access-token";
        private int accessPolicyId;

        private final DataAccessPolicyClient accessPolicyClient = new DataAccessPolicyClient(
                new SdkClientConfiguration
                        .Builder()
                        .endpoint(dataPlaneEndpoint)
                        .authProvider(new AccessTokenAuthProvider(accessToken))
                        .build()
        );

    private DataAccessPolicyResponse createdPolicy;

    private DataAccessPolicyRequest buildTestPolicyRequest(String name) {
        DataAccessPolicyRequest request = new DataAccessPolicyRequest();
        request.setName(name);
        request.setDescription("Test policy for data access");
        request.setEnabled(true);
        request.setPriority("NORMAL");

        DataAccessPolicyRequest.AllowPolicyItem allowPolicyItem = new DataAccessPolicyRequest.AllowPolicyItem();
        allowPolicyItem.setGroups(Arrays.asList("public", "dev"));
        allowPolicyItem.setUsers(Collections.emptyList());
        allowPolicyItem.setAccesses(Arrays.asList("ALL"));
        request.setAllowPolicyItems(Collections.singletonList(allowPolicyItem));

        DataAccessPolicyRequest.Resource resource = new DataAccessPolicyRequest.Resource();
        resource.setDatabases(Collections.singletonList("spark_catalog.*"));
        resource.setTables(Collections.singletonList("*"));
        resource.setColumns(Collections.singletonList("*"));
        resource.setDatabasesInclusionType("INCLUDE");
        resource.setTablesInclusionType("INCLUDE");
        resource.setColumnsInclusionType("INCLUDE");
        request.setResources(Collections.singletonList(resource));

        return request;
    }

    @BeforeEach
    public void setUp() throws IOException {
        // Create a policy to use for testing in update, get, and delete tests
        DataAccessPolicyRequest createRequest = buildTestPolicyRequest("test-policy");
        createdPolicy = accessPolicyClient.createAccessPolicy(createRequest);
        assertThat(createdPolicy.getId()).isNotNull();
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up by deleting the created policy if it exists
        if (createdPolicy != null) {
            accessPolicyClient.deleteAccessPolicy(createdPolicy.getId());
            createdPolicy = null;
        }
    }

    @Test
    public void testCreateAccessPolicy() throws IOException {
        DataAccessPolicyRequest request = buildTestPolicyRequest("create-policy-test");
        DataAccessPolicyResponse response = accessPolicyClient.createAccessPolicy(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("create-policy-test");

        // Clean up the created policy after assertion
        accessPolicyClient.deleteAccessPolicy(response.getId());
    }

    @Test
    public void testUpdateAccessPolicy() throws IOException {
        DataAccessPolicyRequest createRequest = buildTestPolicyRequest("policy-to-update");
        createRequest.setDescription("Initial description");
        DataAccessPolicyResponse createdPolicy = accessPolicyClient.createAccessPolicy(createRequest);

        assertThat(createdPolicy).isNotNull();
        int accessPolicyId = createdPolicy.getId();

        createRequest.setDescription("Updated description");

        DataAccessPolicyResponse updatedPolicy = accessPolicyClient.updateAccessPolicy(accessPolicyId, createRequest);

        assertThat(updatedPolicy).isNotNull();
        assertThat(updatedPolicy.getName()).isEqualTo("policy-to-update");
        assertThat(updatedPolicy.getDescription()).isEqualTo("Updated description");

        accessPolicyClient.deleteAccessPolicy(accessPolicyId);
    }

    @Test
    public void testGetAccessPolicy() throws IOException {
        DataAccessPolicyResponse retrievedPolicy = accessPolicyClient.getAccessPolicy(createdPolicy.getId());

        assertThat(retrievedPolicy).isNotNull();
        assertThat(retrievedPolicy.getId()).isEqualTo(createdPolicy.getId());
        assertThat(retrievedPolicy.getName()).isEqualTo("test-policy");
    }

    @Test
    public void testGetAllAccessPolicies() throws IOException {
        List<DataAccessPolicyResponse> policies = accessPolicyClient.getAccessPolicies();

        assertThat(policies).isNotNull();
        assertThat(policies.size()).isGreaterThan(0);

        // Optional: print policies for debugging
        policies.forEach(policy -> System.out.println("Policy ID: " + policy.getId() + ", Name: " + policy.getName()));
    }

    @Test
    public void testDeleteAccessPolicy() throws IOException {
        // Delete the policy created in setup
        accessPolicyClient.deleteAccessPolicy(createdPolicy.getId());

        // Verify that the policy no longer exists by attempting to retrieve it
        ApiError exception = assertThrows(ApiError.class, () -> accessPolicyClient.getAccessPolicy(createdPolicy.getId()));
        assertThat(exception.getStatus()).isEqualTo(404);  // Assuming 404 Not Found is returned for non-existent policies

        createdPolicy = null; // Set to null to avoid tearDown re-deleting it
    }
}

