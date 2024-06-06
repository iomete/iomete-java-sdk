package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationConfig {
    @JsonProperty
    private boolean isDocker = false;

    private String image;
    private List<String> imagePullSecrets;
    private String mainClass;
    /**
     * Only required if applicationType is python
     * For JVM applications, the default `spark-internal` is used, which tells spark to look at the classpath.
     */
    private String mainApplicationFile = "spark-internal";
    private String applicationType = "jvm"; // jvm, python
    private List<String> arguments;
    private Map<String, String> envVars;
    private String javaOptions;
    private Map<String, String> sparkConf;
    private Dependencies deps;
    private List<ConfigMap> configMaps;
    private InstanceConfig instanceConfig;
    /**
     * Executor's volume configuration.
     * If not set, then hostPath volume will be used.
     * To use PVC volume, create a predefined Volume in IOMETE Console (Settings / Volumes) and provide ID here.
     */
    private String volumeId;

    public ApplicationConfig() {}

    public String getImage() {
        return image;
    }

    public List<String> getImagePullSecrets() {
        return imagePullSecrets;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getMainApplicationFile() {
        return mainApplicationFile;
    }

    public String getApplicationType() {
        return applicationType;
    }

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

    public Dependencies getDeps() {
        return deps;
    }

    public List<ConfigMap> getConfigMaps() {
        return configMaps;
    }

    public InstanceConfig getInstanceConfig() {
        return instanceConfig;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ApplicationConfig applicationConfig = new ApplicationConfig();

        public Builder image(String image) {
            applicationConfig.image = image;
            applicationConfig.isDocker = true;
            return this;
        }

        public Builder imagePullSecrets(List<String> imagePullSecrets) {
            applicationConfig.imagePullSecrets = imagePullSecrets;
            return this;
        }

        public Builder mainClass(String mainClass) {
            applicationConfig.mainClass = mainClass;
            return this;
        }

        public Builder mainApplicationFile(String mainApplicationFile) {
            applicationConfig.mainApplicationFile = mainApplicationFile;
            return this;
        }

        public Builder applicationType(ApplicationType applicationType) {
            applicationConfig.applicationType = applicationType.getValue();
            return this;
        }

        public Builder arguments(List<String> arguments) {
            applicationConfig.arguments = arguments;
            return this;
        }

        public Builder envVars(Map<String, String> envVars) {
            applicationConfig.envVars = envVars;
            return this;
        }

        public Builder javaOptions(String javaOptions) {
            applicationConfig.javaOptions = javaOptions;
            return this;
        }

        public Builder sparkConf(Map<String, String> sparkConf) {
            applicationConfig.sparkConf = sparkConf;
            return this;
        }

        public Builder deps(Dependencies deps) {
            applicationConfig.deps = deps;
            return this;
        }

        public Builder configMaps(List<ConfigMap> configMaps) {
            applicationConfig.configMaps = configMaps;
            return this;
        }

        public Builder instanceConfig(InstanceConfig instanceConfig) {
            applicationConfig.instanceConfig = instanceConfig;
            return this;
        }

        public Builder volumeId(String volumeId) {
            applicationConfig.volumeId = volumeId;
            return this;
        }

        public ApplicationConfig build() {
            return applicationConfig;
        }
    }
}

