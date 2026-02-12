package com.iomete.sdk.spark.job.models;

public enum FlowType {
    LEGACY("Legacy"),
    PRIORITY("Priority");

    private final String value;

    FlowType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
