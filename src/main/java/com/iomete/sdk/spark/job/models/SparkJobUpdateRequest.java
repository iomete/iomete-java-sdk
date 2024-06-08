package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkJobUpdateRequest extends JsonModel<SparkJobUpdateRequest> {
    private String description;
    private String schedule;
    private ConcurrencyState concurrency = ConcurrencyState.FORBID;
    private ApplicationTemplate template = new ApplicationTemplate();
    private List<ResourceTag> resourceTags;

    // Default constructor
    public SparkJobUpdateRequest() {}

    // Getters and Setters
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
        private final SparkJobUpdateRequest sparkJobUpdateRequest = new SparkJobUpdateRequest();

        public Builder description(String description) {
            sparkJobUpdateRequest.description = description;
            return this;
        }

        public Builder schedule(String schedule) {
            sparkJobUpdateRequest.schedule = schedule;
            return this;
        }

        public Builder concurrency(ConcurrencyState concurrency) {
            sparkJobUpdateRequest.concurrency = concurrency;
            return this;
        }

        public Builder template(ApplicationTemplate template) {
            sparkJobUpdateRequest.template = template;
            return this;
        }

        public Builder resourceTags(List<ResourceTag> resourceTags) {
            sparkJobUpdateRequest.resourceTags = resourceTags;
            return this;
        }

        public SparkJobUpdateRequest build() {
            return sparkJobUpdateRequest;
        }
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
