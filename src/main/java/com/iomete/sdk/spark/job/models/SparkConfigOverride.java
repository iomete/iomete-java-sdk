package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iomete.sdk.models.JsonModel;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkConfigOverride extends JsonModel<SparkConfigOverride> {
    private List<String> arguments;
    private Map<String, String> envVars;
    private String javaOptions;
    private Map<String, String> sparkConf;

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public Map<String, String> getEnvVars() {
        return envVars;
    }

    public void setEnvVars(Map<String, String> envVars) {
        this.envVars = envVars;
    }

    public String getJavaOptions() {
        return javaOptions;
    }

    public void setJavaOptions(String javaOptions) {
        this.javaOptions = javaOptions;
    }

    public Map<String, String> getSparkConf() {
        return sparkConf;
    }

    public void setSparkConf(Map<String, String> sparkConf) {
        this.sparkConf = sparkConf;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> arguments;
        private Map<String, String> envVars;
        private String javaOptions;
        private Map<String, String> sparkConf;

        public Builder() {
        }

        public Builder arguments(List<String> arguments) {
            this.arguments = arguments;
            return this;
        }

        public Builder envVars(Map<String, String> envVars) {
            this.envVars = envVars;
            return this;
        }

        public Builder javaOptions(String javaOptions) {
            this.javaOptions = javaOptions;
            return this;
        }

        public Builder sparkConf(Map<String, String> sparkConf) {
            this.sparkConf = sparkConf;
            return this;
        }

        public SparkConfigOverride build() {
            SparkConfigOverride sparkConfigOverride = new SparkConfigOverride();
            sparkConfigOverride.setArguments(arguments);
            sparkConfigOverride.setEnvVars(envVars);
            sparkConfigOverride.setJavaOptions(javaOptions);
            sparkConfigOverride.setSparkConf(sparkConf);
            return sparkConfigOverride;
        }
    }
}

