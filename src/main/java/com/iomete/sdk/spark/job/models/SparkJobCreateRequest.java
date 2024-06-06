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
    /**
     * Cron expression for scheduling the job.
     */
    private String schedule;
    private ConcurrencyState concurrency = ConcurrencyState.FORBID;

    @JsonProperty("template")
    private ApplicationConfig applicationConfig = new ApplicationConfig();

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

    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    public void setApplicationConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SparkJobCreateRequest sparkJobCreateRequest = new SparkJobCreateRequest();

        public Builder name(String name) {
            sparkJobCreateRequest.setName(name);
            return this;
        }

        public Builder description(String description) {
            sparkJobCreateRequest.setDescription(description);
            return this;
        }

        public Builder schedule(String schedule) {
            sparkJobCreateRequest.setSchedule(schedule);
            return this;
        }

        public Builder concurrency(ConcurrencyState concurrency) {
            sparkJobCreateRequest.setConcurrency(concurrency);
            return this;
        }

        public Builder applicationConfig(ApplicationConfig applicationConfig) {
            sparkJobCreateRequest.setApplicationConfig(applicationConfig);
            return this;
        }

        public Builder resourceTags(List<ResourceTag> resourceTags) {
            sparkJobCreateRequest.setResourceTags(resourceTags);
            return this;
        }

        public SparkJobCreateRequest build() {
            return sparkJobCreateRequest;
        }
    }
}
