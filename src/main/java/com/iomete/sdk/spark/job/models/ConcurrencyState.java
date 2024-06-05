package com.iomete.sdk.spark.job.models;

public enum ConcurrencyState {
    ALLOW("Allow"),
    REPLACE("Replace"),
    FORBID("Forbid");

    private final String value;

    ConcurrencyState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
