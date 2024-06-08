package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iomete.sdk.models.JsonModel;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StatusResponse extends JsonModel<StatusResponse> {
    private ApplicationState applicationState = new ApplicationState();
    private Map<String, Integer> executorState = Map.of();
    private String terminationTime;

    public StatusResponse() {
    }

    public StatusResponse(ApplicationState applicationState, Map<String, Integer> executorState, String terminationTime) {
        this.applicationState = applicationState;
        this.executorState = executorState;
        this.terminationTime = terminationTime;
    }

    // Getters and Setters
    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public Map<String, Integer> getExecutorState() {
        return executorState;
    }

    public String getTerminationTime() {
        return terminationTime;
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}
