package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkRunResponse extends JsonModel<SparkRunResponse> {
    private String id;
    private String name;
    private String jobId;
    private String jobName;
    private ApplicationTemplate config;
    private String sparkUI = "";
    private String sparkHistoryUrl = "";

    private List<ResourceTag> resourceTags;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public ApplicationTemplate getConfig() {
        return config;
    }

    public String getSparkUI() {
        return sparkUI;
    }

    public String getSparkHistoryUrl() {
        return sparkHistoryUrl;
    }

    public SparkRunStatus getDriverStatus() {
        return driverStatus;
    }

    public String getDriverErrorMessage() {
        return driverErrorMessage;
    }

    public Map<String, Integer> getExecutorsState() {
        return executorsState;
    }

    public int getTotalExecutors() {
        return totalExecutors;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setConfig(ApplicationTemplate config) {
        this.config = config;
    }

    public void setSparkUI(String sparkUI) {
        this.sparkUI = sparkUI;
    }

    public void setSparkHistoryUrl(String sparkHistoryUrl) {
        this.sparkHistoryUrl = sparkHistoryUrl;
    }

    public void setDriverStatus(SparkRunStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public void setDriverErrorMessage(String driverErrorMessage) {
        this.driverErrorMessage = driverErrorMessage;
    }

    public void setExecutorsState(Map<String, Integer> executorsState) {
        this.executorsState = executorsState;
    }

    public void setTotalExecutors(int totalExecutors) {
        this.totalExecutors = totalExecutors;
    }

    public void setTerminationTime(String terminationTime) {
        this.terminationTime = terminationTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
