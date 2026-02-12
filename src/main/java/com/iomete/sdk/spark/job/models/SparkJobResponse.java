package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkJobResponse extends JsonModel<SparkJobResponse> {
    private String id;
    private String name;
    private String namespace;
    private SparkJobType jobType;
    private String jobUser;
    private Boolean suspend;
    private Bundle bundle;
    private FlowType flow;
    private Priority priority;
    private ConcurrencyState concurrency;
    private SparkJobStatus status;

    private String description;
    private List<ResourceTag> resourceTags;

    private String schedule;

    private ApplicationTemplate template;

    private String createdBy;
    private LocalDateTime createdAt;

    public SparkJobResponse() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public SparkJobType getJobType() {
        return jobType;
    }

    public void setJobType(SparkJobType jobType) {
        this.jobType = jobType;
    }

    public String getJobUser() {
        return jobUser;
    }

    public void setJobUser(String jobUser) {
        this.jobUser = jobUser;
    }

    public Boolean getSuspend() {
        return suspend;
    }

    public void setSuspend(Boolean suspend) {
        this.suspend = suspend;
    }

    public ConcurrencyState getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(ConcurrencyState concurrency) {
        this.concurrency = concurrency;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public FlowType getFlow() {
        return flow;
    }

    public void setFlow(FlowType flow) {
        this.flow = flow;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public SparkJobStatus getStatus() {
        return status;
    }

    public void setStatus(SparkJobStatus status) {
        this.status = status;
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

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ApplicationTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ApplicationTemplate template) {
        this.template = template;
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

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
