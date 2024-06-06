package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public ConcurrencyState getConcurrency() {
        return concurrency;
    }

    public ApplicationConfig getSparkConfig() {
        return applicationConfig;
    }

    public SparkJobStatus getStatus() {
        return status;
    }

    public SparkRunResponse getLastRun() {
        return lastRun;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }
}
