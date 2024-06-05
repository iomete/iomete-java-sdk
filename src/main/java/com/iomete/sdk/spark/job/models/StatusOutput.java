package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StatusOutput {
    private ApplicationState applicationState = new ApplicationState();
    private Map<String, Integer> executorState = Map.of();
    private String terminationTime;

    public StatusOutput() {
    }

    public StatusOutput(ApplicationState applicationState, Map<String, Integer> executorState, String terminationTime) {
        this.applicationState = applicationState;
        this.executorState = executorState;
        this.terminationTime = terminationTime;
    }

    // Getters and Setters
    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public Map<String, Integer> getExecutorState() {
        return executorState;
    }

    public void setExecutorState(Map<String, Integer> executorState) {
        this.executorState = executorState;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(String terminationTime) {
        this.terminationTime = terminationTime;
    }
}
