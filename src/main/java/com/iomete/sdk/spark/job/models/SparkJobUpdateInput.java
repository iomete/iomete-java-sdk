package com.iomete.sdk.spark.job.models;

import com.iomete.sdk.models.ResourceTag;

import java.util.List;

/**
 * Model representing input for updating a Spark job.
 */
public class SparkJobUpdateInput {
    /**
     * Description of the Spark job.
     */
    private String description;

    /**
     * Schedule of the Spark job.
     */
    private String schedule;

    /**
     * Concurrency state of the Spark job.
     */
    private ConcurrencyState concurrency;

    /**
     * Template configuration for the Spark job.
     */
    private SparkConfig template = new SparkConfig();

    /**
     * List of resource tags associated with the Spark job.
     */
    private List<ResourceTag> resourceTags;

    // Default constructor
    public SparkJobUpdateInput() {
    }

    // Getters and Setters
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

    public SparkConfig getTemplate() {
        return template;
    }

    public void setTemplate(SparkConfig template) {
        this.template = template;
    }

    public List<ResourceTag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(List<ResourceTag> resourceTags) {
        this.resourceTags = resourceTags;
    }
}

