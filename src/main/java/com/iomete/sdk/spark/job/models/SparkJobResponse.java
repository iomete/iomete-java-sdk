package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkJobResponse extends JsonModel<SparkJobResponse> {
    private String id;
    private String name;
    private String description;
    private String schedule;
    private ConcurrencyState concurrency;
    @JsonProperty("template")
    private ApplicationConfig applicationConfig;
    private SparkJobStatus status;
    private SparkRunResponse lastRun;
    private List<ResourceTag> resourceTags;

    private String createdBy;
    private LocalDateTime createdAt;

    public SparkJobResponse() {}

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ConcurrencyState getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(ConcurrencyState concurrency) {
        this.concurrency = concurrency;
    }

    public ApplicationConfig getSparkConfig() {
        return applicationConfig;
    }

    public void setSparkConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public SparkJobStatus getStatus() {
        return status;
    }

    public void setStatus(SparkJobStatus status) {
        this.status = status;
    }

    public SparkRunResponse getLastRun() {
        return lastRun;
    }

    public void setLastRun(SparkRunResponse lastRun) {
        this.lastRun = lastRun;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }
}
