package com.iomete.sdk.lakehouse.model;


public class LakehouseClusterCreateRequest {
    private String name;
    private String namespace;
    private String type;
    private NodeTypes nodeTypes;
    private AutoScale autoScale;

    // Inner classes for NodeTypes and AutoScale
    public static class NodeTypes {
        private String driver;
        private String executor;

        // Getters and setters
        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getExecutor() {
            return executor;
        }

        public void setExecutor(String executor) {
            this.executor = executor;
        }
    }

    public static class AutoScale {
        private boolean enabled;
        private int idleTimeoutInSeconds;

        // Getters and setters
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getIdleTimeoutInSeconds() {
            return idleTimeoutInSeconds;
        }

        public void setIdleTimeoutInSeconds(int idleTimeoutInSeconds) {
            this.idleTimeoutInSeconds = idleTimeoutInSeconds;
        }
    }

    // Getters and setters for main fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NodeTypes getNodeTypes() {
        return nodeTypes;
    }

    public void setNodeTypes(NodeTypes nodeTypes) {
        this.nodeTypes = nodeTypes;
    }

    public AutoScale getAutoScale() {
        return autoScale;
    }

    public void setAutoScale(AutoScale autoScale) {
        this.autoScale = autoScale;
    }
}
