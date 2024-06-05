package com.iomete.sdk.spark.job.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkConfig {
    private String sparkVersion;
    private boolean isDocker = true;
    private String image;
    private List<String> imagePullSecrets;
    private String mainClass;
    private String mainApplicationFile = "spark-internal";
    private String applicationType = "jvm"; // jvm, python
    private List<String> arguments;
    private Map<String, String> envVars;
    private String javaOptions;
    private Map<String, String> sparkConf;
    private Dependencies deps;
    private List<ConfigMap> configMaps;
    private InstanceConfig instanceConfig;
    private String volumeId;

    public String getSparkVersion() {
        return sparkVersion;
    }

    public void setSparkVersion(String sparkVersion) {
        this.sparkVersion = sparkVersion;
    }

    public boolean isDocker() {
        return isDocker;
    }

    public void setIsDocker(boolean docker) {
        isDocker = docker;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}

