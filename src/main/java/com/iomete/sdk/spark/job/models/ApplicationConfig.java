package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        this.isDocker = true;
    }

    public List<String> getImagePullSecrets() {
        return imagePullSecrets;
    }

    public void setImagePullSecrets(List<String> imagePullSecrets) {
        this.imagePullSecrets = imagePullSecrets;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getMainApplicationFile() {
        return mainApplicationFile;
    }

    public void setMainApplicationFile(String mainApplicationFile) {
        this.mainApplicationFile = mainApplicationFile;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType.getValue();
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

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

    public Dependencies getDeps() {
        return deps;
    }

    public void setDeps(Dependencies deps) {
        this.deps = deps;
    }

    public List<ConfigMap> getConfigMaps() {
        return configMaps;
    }

    public void setConfigMaps(List<ConfigMap> configMaps) {
        this.configMaps = configMaps;
    }

    public InstanceConfig getInstanceConfig() {
        return instanceConfig;
    }

    public void setInstanceConfig(InstanceConfig instanceConfig) {
        this.instanceConfig = instanceConfig;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ApplicationConfig applicationConfig = new ApplicationConfig();

        public Builder image(String image) {
            applicationConfig.setImage(image);
            return this;
        }

        public Builder imagePullSecrets(List<String> imagePullSecrets) {
            applicationConfig.setImagePullSecrets(imagePullSecrets);
            return this;
        }

        public Builder mainClass(String mainClass) {
            applicationConfig.setMainClass(mainClass);
            return this;
        }

        public Builder mainApplicationFile(String mainApplicationFile) {
            applicationConfig.setMainApplicationFile(mainApplicationFile);
            return this;
        }

        public Builder applicationType(ApplicationType applicationType) {
            applicationConfig.setApplicationType(applicationType);
            return this;
        }

        public Builder arguments(List<String> arguments) {
            applicationConfig.setArguments(arguments);
            return this;
        }

        public Builder envVars(Map<String, String> envVars) {
            applicationConfig.setEnvVars(envVars);
            return this;
        }

        public Builder javaOptions(String javaOptions) {
            applicationConfig.setJavaOptions(javaOptions);
            return this;
        }

        public Builder sparkConf(Map<String, String> sparkConf) {
            applicationConfig.setSparkConf(sparkConf);
            return this;
        }

        public Builder deps(Dependencies deps) {
            applicationConfig.setDeps(deps);
            return this;
        }

        public Builder configMaps(List<ConfigMap> configMaps) {
            applicationConfig.setConfigMaps(configMaps);
            return this;
        }

        public Builder instanceConfig(InstanceConfig instanceConfig) {
            applicationConfig.setInstanceConfig(instanceConfig);
            return this;
        }

        public Builder volumeId(String volumeId) {
            applicationConfig.setVolumeId(volumeId);
            return this;
        }

        public ApplicationConfig build() {
            return applicationConfig;
        }
    }
}

