package com.iomete.sdk.spark.job.models;

public class RestartPolicy {

    private String type = "Never";

    private Integer onSubmissionFailureRetries;
    private Integer onFailureRetries;
    private Integer onSubmissionFailureRetryInterval;
    private Integer onFailureRetryInterval;

    public RestartPolicy() {
    }

    public RestartPolicy(String type,
                         Integer onSubmissionFailureRetries,
                         Integer onFailureRetries,
                         Integer onSubmissionFailureRetryInterval,
                         Integer onFailureRetryInterval) {
        this.type = (type != null) ? type : "Never";
        this.onSubmissionFailureRetries = onSubmissionFailureRetries;
        this.onFailureRetries = onFailureRetries;
        this.onSubmissionFailureRetryInterval = onSubmissionFailureRetryInterval;
        this.onFailureRetryInterval = onFailureRetryInterval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOnSubmissionFailureRetries() {
        return onSubmissionFailureRetries;
    }

    public void setOnSubmissionFailureRetries(Integer onSubmissionFailureRetries) {
        this.onSubmissionFailureRetries = onSubmissionFailureRetries;
    }

    public Integer getOnFailureRetries() {
        return onFailureRetries;
    }

    public void setOnFailureRetries(Integer onFailureRetries) {
        this.onFailureRetries = onFailureRetries;
    }

    public Integer getOnSubmissionFailureRetryInterval() {
        return onSubmissionFailureRetryInterval;
    }

    public void setOnSubmissionFailureRetryInterval(Integer onSubmissionFailureRetryInterval) {
        this.onSubmissionFailureRetryInterval = onSubmissionFailureRetryInterval;
    }

    public Integer getOnFailureRetryInterval() {
        return onFailureRetryInterval;
    }

    public void setOnFailureRetryInterval(Integer onFailureRetryInterval) {
        this.onFailureRetryInterval = onFailureRetryInterval;
    }
}

