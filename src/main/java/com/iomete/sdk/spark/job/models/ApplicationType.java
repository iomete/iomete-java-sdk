package com.iomete.sdk.spark.job.models;

public enum ApplicationType {
    JVM("jvm"),
    PYTHON("python");

    private final String value;

    ApplicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
