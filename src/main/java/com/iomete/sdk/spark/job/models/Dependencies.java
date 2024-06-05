package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependencies {
    private List<String> jars;
    private List<String> files;
    private List<String> pyFiles;
    private List<String> packages;
    private List<String> excludePackages;
    private List<String> repositories;

    public Dependencies() {
    }

    public Dependencies(List<String> jars, List<String> files, List<String> pyFiles, List<String> packages, List<String> excludePackages, List<String> repositories) {
        this.jars = jars;
        this.files = files;
        this.pyFiles = pyFiles;
        this.packages = packages;
        this.excludePackages = excludePackages;
        this.repositories = repositories;
    }

    // Getters and Setters
    public List<String> getJars() {
        return jars;
    }

    public void setJars(List<String> jars) {
        this.jars = jars;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getPyFiles() {
        return pyFiles;
    }

    public void setPyFiles(List<String> pyFiles) {
        this.pyFiles = pyFiles;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public List<String> getExcludePackages() {
        return excludePackages;
    }

    public void setExcludePackages(List<String> excludePackages) {
        this.excludePackages = excludePackages;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<String> repositories) {
        this.repositories = repositories;
    }
}

