package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iomete.sdk.models.JsonModel;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkConfigOverride extends JsonModel<SparkConfigOverride> {
    private List<String> arguments;
    private Map<String, String> envVars;
    private String javaOptions;
    private Map<String, String> sparkConf;

    public SparkConfigOverride() {}

    public List<String> getArguments() {
        return arguments;
    }

    public Map<String, String> getEnvVars() {
        return envVars;
    }

    public String getJavaOptions() {
        return javaOptions;
    }

    public Map<String, String> getSparkConf() {
        return sparkConf;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SparkConfigOverride sparkConfigOverride = new SparkConfigOverride();

        public Builder arguments(List<String> arguments) {
            sparkConfigOverride.arguments = arguments;
            return this;
        }

        public Builder envVars(Map<String, String> envVars) {
            sparkConfigOverride.envVars = envVars;
            return this;
        }

        public Builder javaOptions(String javaOptions) {
            sparkConfigOverride.javaOptions = javaOptions;
            return this;
        }

        public Builder sparkConf(Map<String, String> sparkConf) {
            sparkConfigOverride.sparkConf = sparkConf;
            return this;
        }

        public SparkConfigOverride build() {
            return sparkConfigOverride;
        }
    }

    @Override
    public String toString() {
        return this.toJson(true);
    }
}

