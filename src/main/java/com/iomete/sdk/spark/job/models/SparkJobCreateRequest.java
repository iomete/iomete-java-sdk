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
    private ApplicationTemplate template = new ApplicationTemplate();
    private List<ResourceTag> resourceTags;

    // Default constructor
    public SparkJobCreateRequest() {}

    // Getters and Setters
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

    public ApplicationTemplate getTemplate() {
        return template;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SparkJobCreateRequest sparkJobCreateRequest = new SparkJobCreateRequest();

        public Builder name(String name) {
            sparkJobCreateRequest.name = name;
            return this;
        }

        public Builder description(String description) {
            sparkJobCreateRequest.description = description;
            return this;
        }

        public Builder schedule(String schedule) {
            sparkJobCreateRequest.schedule = schedule;
            return this;
        }

        public Builder concurrency(ConcurrencyState concurrency) {
            sparkJobCreateRequest.concurrency = concurrency;
            return this;
        }

        public Builder template(ApplicationTemplate template) {
            sparkJobCreateRequest.template = template;
            return this;
        }

        public Builder resourceTags(List<ResourceTag> resourceTags) {
            sparkJobCreateRequest.resourceTags = resourceTags;
            return this;
        }

        public SparkJobCreateRequest build() {
            return sparkJobCreateRequest;
        }
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
