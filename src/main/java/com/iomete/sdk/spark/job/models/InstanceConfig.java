package com.iomete.sdk.spark.job.models;


public class InstanceConfig {
    /**
     * Name of the Driver Node.
     * Nodes are defined in IOMETE Console (Settings / Node Types).
     */
    private String driverType;
    private String executorType;
    private int executorCount = 1;

    public InstanceConfig(String driverType, String executorType) {
        this.driverType = driverType;
        this.executorType = executorType;
    }

    public InstanceConfig(String driverType, String executorType, int executorCount) {
        this.driverType = driverType;
        this.executorType = executorType;
        this.executorCount = executorCount;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }

    public int getExecutorCount() {
        return executorCount;
    }

    public void setExecutorCount(int executorCount) {
        this.executorCount = executorCount;
    }
}
