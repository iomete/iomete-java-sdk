package com.iomete.sdk.models;

public class ResourceTag {
    private String key;
    private String value;

    public ResourceTag() {
    }

    public ResourceTag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getters and Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
