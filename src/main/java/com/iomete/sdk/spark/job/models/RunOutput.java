package com.iomete.sdk.spark.job.models;

import java.time.LocalDateTime;
import java.util.Map;

public class RunOutput {
    private String id = "";
    private String createdBy = "";
    private LocalDateTime createdAt;
    private String name = "";
    private String jobId = "";
    private String jobName;
    private Object config;
    private StatusOutput status;
    private String sparkUI = "";
    private String sparkHistoryUrl = "";
    private SparkRunStatus driverStatus;
    private String driverErrorMessage;
    private Map<String, Integer> executorsState = Map.of();
    private int totalExecutors = 1;
    private String terminationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Object getConfig() {
        return config;
    }

    public void setConfig(Object config) {
        this.config = config;
    }

    public StatusOutput getStatus() {
        return status;
    }

    public void setStatus(StatusOutput status) {
        this.status = status;
    }

    public String getSparkUI() {
        return sparkUI;
    }

    public void setSparkUI(String sparkUI) {
        this.sparkUI = sparkUI;
    }

    public String getSparkHistoryUrl() {
        return sparkHistoryUrl;
    }

    public void setSparkHistoryUrl(String sparkHistoryUrl) {
        this.sparkHistoryUrl = sparkHistoryUrl;
    }

    public SparkRunStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(SparkRunStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getDriverErrorMessage() {
        return driverErrorMessage;
    }

    public void setDriverErrorMessage(String driverErrorMessage) {
        this.driverErrorMessage = driverErrorMessage;
    }

    public Map<String, Integer> getExecutorsState() {
        return executorsState;
    }

    public void setExecutorsState(Map<String, Integer> executorsState) {
        this.executorsState = executorsState;
    }

    public int getTotalExecutors() {
        return totalExecutors;
    }

    public void setTotalExecutors(int totalExecutors) {
        this.totalExecutors = totalExecutors;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(String terminationTime) {
        this.terminationTime = terminationTime;
    }
}

