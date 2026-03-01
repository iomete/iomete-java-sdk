package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String namespace;
    private String description;
    private String jobUser;
    private SparkJobType jobType;
    /**
     * Cron expression for scheduling the job.
     */
    private String schedule;
    private String bundleId;
    private FlowType flow = FlowType.LEGACY;
    private Priority priority = Priority.NORMAL;
    private ConcurrencyState concurrency = ConcurrencyState.FORBID;
    private ApplicationTemplate template = new ApplicationTemplate();
    private List<ResourceTag> resourceTags;

    // Default constructor
    public SparkJobCreateRequest() {}

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getJobUser() {
        return jobUser;
    }

    public SparkJobType getJobType() {
        return jobType;
    }

    public String getDescription() {
        return description;
    }

    public String getBundleId() {
        return bundleId;
    }

    public FlowType getFlow() {
        return flow;
    }

    public Priority getPriority() {
        return priority;
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

        public Builder namespace(String namespace) {
            sparkJobCreateRequest.namespace = namespace;
            return this;
        }

        public Builder jobUser(String jobUser) {
            sparkJobCreateRequest.jobUser = jobUser;
            return this;
        }

        public Builder jobType(SparkJobType jobType) {
            sparkJobCreateRequest.jobType = jobType;
            return this;
        }

        public Builder description(String description) {
            sparkJobCreateRequest.description = description;
            return this;
        }

        public Builder bundleId(String bundleId) {
            sparkJobCreateRequest.bundleId = bundleId;
            return this;
        }

        public Builder flow(FlowType flow) {
            sparkJobCreateRequest.flow = flow;
            return this;
        }

        public Builder priority(Priority priority) {
            sparkJobCreateRequest.priority = priority;
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
