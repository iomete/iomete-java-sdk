package com.iomete.sdk.spark.job.models;

import com.iomete.sdk.models.JsonModel;
import com.iomete.sdk.models.ResourceTag;

import java.time.LocalDateTime;
import java.util.List;

public class SparkJobResponse extends JsonModel<SparkJobResponse> {
    private String id;
    private String createdBy;
    private LocalDateTime createdAt;
    private String name;
    private String description;
    private SparkJobType jobType;
    private String schedule;
    private ConcurrencyState concurrency;
    private Object template;
    private Object status;
    private RunOutput lastRun;
    private List<ResourceTag> resourceTags;

    public SparkJobResponse() {
        this.id = "";
        this.createdBy = "";
        this.createdAt = null;
        this.name = "";
        this.description = null;
        this.jobType = SparkJobType.MANUAL;
        this.schedule = null;
        this.concurrency = null;
        this.template = null;
        this.status = null;
        this.lastRun = null;
        this.resourceTags = null;
    }

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

    public SparkJobType getJobType() {
        return jobType;
    }

    public void setJobType(SparkJobType jobType) {
        this.jobType = jobType;
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

    public Object getTemplate() {
        return template;
    }

    public void setTemplate(Object template) {
        this.template = template;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public RunOutput getLastRun() {
        return lastRun;
    }

    public void setLastRun(RunOutput lastRun) {
        this.lastRun = lastRun;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }
}
