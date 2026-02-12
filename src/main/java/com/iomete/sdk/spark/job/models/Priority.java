package com.iomete.sdk.spark.job.models;

public enum Priority {
    NORMAL("Normal"),
    HIGH("High");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
