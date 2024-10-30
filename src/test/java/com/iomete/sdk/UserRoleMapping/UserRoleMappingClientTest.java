package com.iomete.sdk.UserRoleMapping;

import com.iomete.sdk.auth.AccessTokenAuthProvider;
import com.iomete.sdk.client.SdkClientConfiguration;
import com.iomete.sdk.iam.groups.GroupClient;
import com.iomete.sdk.iam.groups.models.CreateGroupRequest;
import com.iomete.sdk.iam.roles.RoleClient;
import com.iomete.sdk.iam.roles.models.CreateRoleRequest;
import com.iomete.sdk.iam.userRoleGroupMappings.UserRoleMappingClient;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserInfoResponse;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserRoleResponse;
import com.iomete.sdk.iam.userRoleGroupMappings.models.UserGroupResponse;
import com.iomete.sdk.iam.users.UserClient;
import com.iomete.sdk.iam.users.models.CreateUserRequest;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserRoleMappingClientTest {
    private static final Logger logger = Logger.getLogger(UserRoleMappingClientTest.class.getName());

    private final String dataPlaneEndpoint = "https://dev.iomete.cloud";
    private final String accessToken = "auth-token";

    private final UserRoleMappingClient userRoleMappingClient = new UserRoleMappingClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );
    private final UserClient userClient = new UserClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );
    private final GroupClient groupClient = new GroupClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );
    private final RoleClient roleClient = new RoleClient(
            new SdkClientConfiguration
                    .Builder()
                    .endpoint(dataPlaneEndpoint)
                    .authProvider(new AccessTokenAuthProvider(accessToken))
                    .build()
    );

    private UserInfoResponse testUser;
    private String testGroupName = "test-group-sdk";
    private String testRoleName = "test-role-sdk";

    @BeforeEach
    //creating test user with no groups no roles, new group and new role
    public void setUp() throws IOException {
        CreateUserRequest createUserRequest = new CreateUserRequest("test_user@iomete.com", "testusersdk", "Test", "User SDK");
        userClient.createUser(createUserRequest);
        testUser = userRoleMappingClient.getUser("testusersdk");
        CreateGroupRequest createGroupRequest = new CreateGroupRequest(testGroupName, "Test group description");
        groupClient.createGroup(createGroupRequest);

        CreateRoleRequest createRoleRequest = new CreateRoleRequest(testRoleName, "Test role description");
        roleClient.createRole(createRoleRequest);
    }

    @AfterEach
    //deleting test user, new group and new role
    public void tearDown() throws IOException {
        if (testUser != null) {
            userClient.deleteUser(testUser.getItem().getUsername());
        }
        groupClient.deleteGroup(testGroupName);
        roleClient.deleteRole(testRoleName);
    }

    @Test
    // Retrieve user information
    public void testGetUser() throws IOException {
        UserInfoResponse user = userRoleMappingClient.getUser("testuser");
        assertThat(user).isNotNull();
        assertThat(user.getItem().getUsername()).isEqualTo("testuser");
        logger.info("Retrieved user: " + user.getItem().getUsername());
    }

    @Test
    // Add the user to a group and test
    public void testAddAndRetrieveUserGroups() throws IOException {
        userRoleMappingClient.addGroups("testuser", Collections.singletonList(testGroupName));

        List<UserGroupResponse> groups = userRoleMappingClient.getUserGroups("testuser");
        assertThat(groups).isNotNull();
        assertThat(groups).anyMatch(group -> group.getName().equals(testGroupName));

        logger.info("Groups for user retrieved successfully.");
    }

    @Test
    // Remove user from a group and test
    public void testRemoveUserGroups() throws IOException {
        userRoleMappingClient.addGroups("testuser", Collections.singletonList(testGroupName));
        userRoleMappingClient.removeGroups("testuser", Collections.singletonList(testGroupName));

        List<UserGroupResponse> groups = userRoleMappingClient.getUserGroups("testuser");
        assertThat(groups).noneMatch(group -> group.getName().equals(testGroupName));

        logger.info("Group removal verified successfully.");
    }

    @Test
    // Add a role to the user and test
    public void testAddAndRetrieveUserRoles() throws IOException {
        userRoleMappingClient.addRoles("testuser", Collections.singletonList(testRoleName));

        List<UserRoleResponse> roles = userRoleMappingClient.getUserRoles("testuser");
        assertThat(roles).isNotNull();
        assertThat(roles).anyMatch(role -> role.getName().equals(testRoleName));

        logger.info("Roles for user retrieved successfully.");
    }

    @Test
    // Remove the role applied to user and test
    public void testRemoveUserRoles() throws IOException {
        userRoleMappingClient.addRoles("testuser", Collections.singletonList(testRoleName));
        userRoleMappingClient.removeRoles("testuser", Collections.singletonList(testRoleName));

        List<UserRoleResponse> roles = userRoleMappingClient.getUserRoles("testuser");
        assertThat(roles).noneMatch(role -> role.getName().equals(testRoleName));

        logger.info("Role removal verified successfully.");
    }
}
