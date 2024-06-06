package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iomete.sdk.models.JsonModel;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkRunResponse extends JsonModel<SparkRunResponse> {
    private String id;
    private String name;
    private String jobId;
    private String jobName;
    private ApplicationConfig config;
    private String sparkUI = "";
    private String sparkHistoryUrl = "";
    private SparkRunStatus driverStatus;
    private String driverErrorMessage;
    private Map<String, Integer> executorsState = Map.of();
    private int totalExecutors = 1;
    private String terminationTime;

    private String createdBy = "";
    private LocalDateTime createdAt;

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

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public ApplicationConfig getConfig() {
        return config;
    }

    public String getSparkUI() {
        return sparkUI;
    }

    public String getSparkHistoryUrl() {
        return sparkHistoryUrl;
    }

    public SparkRunStatus getDriverStatus() {
        return driverStatus;
    }

    public String getDriverErrorMessage() {
        return driverErrorMessage;
    }

    public Map<String, Integer> getExecutorsState() {
        return executorsState;
    }

    public int getTotalExecutors() {
        return totalExecutors;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

}
