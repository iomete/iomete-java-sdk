package com.iomete.sdk.lakehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.Map;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown fields during deserialization
public class LakehouseClusterResponse {
    private String id;
    private String name;
    private String description;
    private String namespace;
    private NodeTypes nodeTypes;
    private String volumeId;
    private SparkConfig sparkConfig;
    private boolean isDeleted;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private String jdbcUrl;
    private String sparkUI;
    private String metricsUI;
    private AutoScale autoScale;
    private String driverStatus;
    private String driverErrorMessage;
    private Map<String, String> executorsState;
    private int totalExecutors;

    // Inner class to represent NodeTypes
    public static class NodeTypes {
        private String driver;
        private String executor;
        private int minExecutors;
        private int maxExecutors;
        private boolean hasSpotSelected;

        // Getters and Setters
        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getExecutor() {
            return executor;
        }

        public void setExecutor(String executor) {
            this.executor = executor;
        }

        public int getMinExecutors() {
            return minExecutors;
        }

        public void setMinExecutors(int minExecutors) {
            this.minExecutors = minExecutors;
        }

        public int getMaxExecutors() {
            return maxExecutors;
        }

        public void setMaxExecutors(int maxExecutors) {
            this.maxExecutors = maxExecutors;
        }

        public boolean isHasSpotSelected() {
            return hasSpotSelected;
        }

        public void setHasSpotSelected(boolean hasSpotSelected) {
            this.hasSpotSelected = hasSpotSelected;
        }
    }

    // Inner class to represent SparkConfig
    public static class SparkConfig {
        private Map<String, String> envVars;
        private Map<String, String> sparkConf;
        private Map<String, String> deps;

        // Getters and Setters
        public Map<String, String> getEnvVars() {
            return envVars;
        }

        public void setEnvVars(Map<String, String> envVars) {
            this.envVars = envVars;
        }

        public Map<String, String> getSparkConf() {
            return sparkConf;
        }

        public void setSparkConf(Map<String, String> sparkConf) {
            this.sparkConf = sparkConf;
        }

        public Map<String, String> getDeps() {
            return deps;
        }

        public void setDeps(Map<String, String> deps) {
            this.deps = deps;
        }
    }

    // Inner class to represent AutoScale
    public static class AutoScale {
        private boolean enabled;
        private int idleTimeoutInSeconds;

        // Getters and Setters
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getIdleTimeoutInSeconds() {
            return idleTimeoutInSeconds;
        }

        public void setIdleTimeoutInSeconds(int idleTimeoutInSeconds) {
            this.idleTimeoutInSeconds = idleTimeoutInSeconds;
        }
    }

    // Getters and Setters for main fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public NodeTypes getNodeTypes() {
        return nodeTypes;
    }

    public void setNodeTypes(NodeTypes nodeTypes) {
        this.nodeTypes = nodeTypes;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public SparkConfig getSparkConfig() {
        return sparkConfig;
    }

    public void setSparkConfig(SparkConfig sparkConfig) {
        this.sparkConfig = sparkConfig;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getSparkUI() {
        return sparkUI;
    }

    public void setSparkUI(String sparkUI) {
        this.sparkUI = sparkUI;
    }

    public String getMetricsUI() {
        return metricsUI;
    }

    public void setMetricsUI(String metricsUI) {
        this.metricsUI = metricsUI;
    }

    public AutoScale getAutoScale() {
        return autoScale;
    }

    public void setAutoScale(AutoScale autoScale) {
        this.autoScale = autoScale;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getDriverErrorMessage() {
        return driverErrorMessage;
    }

    public void setDriverErrorMessage(String driverErrorMessage) {
        this.driverErrorMessage = driverErrorMessage;
    }

    public Map<String, String> getExecutorsState() {
        return executorsState;
    }

    public void setExecutorsState(Map<String, String> executorsState) {
        this.executorsState = executorsState;
    }

    public int getTotalExecutors() {
        return totalExecutors;
    }

    public void setTotalExecutors(int totalExecutors) {
        this.totalExecutors = totalExecutors;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "LakehouseClusterResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", namespace='" + namespace + '\'' +
                ", nodeTypes=" + nodeTypes +
                ", volumeId='" + volumeId + '\'' +
                ", sparkConfig=" + sparkConfig +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", sparkUI='" + sparkUI + '\'' +
                ", metricsUI='" + metricsUI + '\'' +
                ", autoScale=" + autoScale +
                ", driverStatus='" + driverStatus + '\'' +
                ", driverErrorMessage='" + driverErrorMessage + '\'' +
                ", executorsState=" + executorsState +
                ", totalExecutors=" + totalExecutors +
                '}';
    }
}
