package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iomete.sdk.models.JsonModel;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkRunResponse extends JsonModel<SparkRunResponse> {
    private String id;
    private String name;
    private String jobId;
    private String jobName;
    private ApplicationConfig config;
    private String sparkUI = "";
    private String sparkHistoryUrl = "";
    private SparkRunStatus driverStatus;
    private String driverErrorMessage;
    private Map<String, Integer> executorsState = Map.of();
    private int totalExecutors = 1;
    private String terminationTime;

    private String createdBy = "";
    private LocalDateTime createdAt;

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

    public ApplicationConfig getConfig() {
        return config;
    }

    public void setConfig(ApplicationConfig config) {
        this.config = config;
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

