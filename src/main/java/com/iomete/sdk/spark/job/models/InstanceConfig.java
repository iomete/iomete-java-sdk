package com.iomete.sdk.spark.job.models;


public class InstanceConfig {
    private String driverType;
    private String executorType;
    private int executorCount = 2;

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
