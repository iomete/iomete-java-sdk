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
    private String pythonScript;

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

    public String getPythonScript() {
        return pythonScript;
    }

    public void setPythonScript(String pythonScript) {
        this.pythonScript = pythonScript;
    }
}

