package com.iomete.sdk.spark.job.models;


import com.iomete.sdk.models.JsonModel;

public class InstanceConfig extends JsonModel<InstanceConfig> {
    /**
     * Name of the Driver Node.
     * Nodes are defined in IOMETE Console (Settings / Node Types).
     */
    private String driverType;
    private String executorType;
    private int executorCount = 1;
    private boolean singleNodeDeployment = false;

    public InstanceConfig() {}

    public InstanceConfig(String driverType, String executorType) {
        this.driverType = driverType;
        this.executorType = executorType;
    }

    public InstanceConfig(String driverType, String executorType, int executorCount) {
        this.driverType = driverType;
        this.executorType = executorType;
        this.executorCount = executorCount;
    }

    public InstanceConfig(String driverType) {
        this.driverType = driverType;
        this.singleNodeDeployment = true;
    }

    public String getDriverType() {
        return driverType;
    }

    public String getExecutorType() {
        return executorType;
    }

    public int getExecutorCount() {
        return executorCount;
    }

    public boolean isSingleNodeDeployment() {
        return singleNodeDeployment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String driverType;
        private String executorType;
        private int executorCount = 1;

        public Builder driverType(String driverType) {
            this.driverType = driverType;
            return this;
        }

        public Builder executorType(String executorType) {
            this.executorType = executorType;
            return this;
        }

        public Builder executorCount(int executorCount) {
            this.executorCount = executorCount;
            return this;
        }

        public InstanceConfig build() {
            return new InstanceConfig(driverType, executorType, executorCount);
        }

        public InstanceConfig buildSingleNode() {
            return new InstanceConfig(driverType);
        }
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
