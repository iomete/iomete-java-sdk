package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dependencies {
    private List<String> jars;
    private List<String> files;
    private List<String> pyFiles;
    private List<String> packages;
    private List<String> repositories;

    public Dependencies() {}

    public Dependencies(List<String> jars, List<String> files, List<String> pyFiles, List<String> packages, List<String> repositories) {
        this.jars = jars;
        this.files = files;
        this.pyFiles = pyFiles;
        this.packages = packages;
        this.repositories = repositories;
    }

    // Getters and Setters
    public List<String> getJars() {
        return jars;
    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getPyFiles() {
        return pyFiles;
    }

    public List<String> getPackages() {
        return packages;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public static class Builder {
        private final Dependencies dependencies = new Dependencies();

        public Builder jars(List<String> jars) {
            dependencies.jars = jars;
            return this;
        }

        public Builder files(List<String> files) {
            dependencies.files = files;
            return this;
        }

        public Builder pyFiles(List<String> pyFiles) {
            dependencies.pyFiles = pyFiles;
            return this;
        }

        public Builder packages(List<String> packages) {
            dependencies.packages = packages;
            return this;
        }

        public Builder repositories(List<String> repositories) {
            dependencies.repositories = repositories;
            return this;
        }

        public Dependencies build() {
            return dependencies;
        }
    }
}

