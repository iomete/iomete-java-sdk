package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkJobStatus {
    private String lastRun;
    private String lastRunName;
    private String nextRun;
    private String scheduleState;

    public SparkJobStatus() {}

    public String getLastRun() {
        return lastRun;
    }

    public String getLastRunName() {
        return lastRunName;
    }

    public String getNextRun() {
        return nextRun;
    }

    public String getScheduleState() {
        return scheduleState;
    }
}
