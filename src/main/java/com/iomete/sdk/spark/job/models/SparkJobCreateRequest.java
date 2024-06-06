package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkJobCreateRequest extends JsonModel<SparkJobCreateRequest> {
    /**
     * Name of the Spark job.
     * - Must be between 3 and 64 characters.
     * - Should be unique.
     */
    private String name;
    private String description;
    private String schedule;
    private ConcurrencyState concurrency = ConcurrencyState.FORBID;
    @JsonProperty("template")
    private SparkConfig sparkConfig = new SparkConfig();
    private List<ResourceTag> resourceTags;

    // Default constructor
    public SparkJobCreateRequest() {
    }

    // Getters and Setters
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

    public SparkConfig getSparkConfig() {
        return sparkConfig;
    }

    public void setSparkConfig(SparkConfig sparkConfig) {
        this.sparkConfig = sparkConfig;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }
}
