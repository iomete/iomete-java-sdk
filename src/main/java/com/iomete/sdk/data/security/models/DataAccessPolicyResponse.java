package com.iomete.sdk.data.security.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataAccessPolicyResponse {
    private int id;
    private boolean isEnabled;
    private String name;
    private String description;
    private String priority;
    private List<Resource> resources;
    private List<AllowPolicyItem> allowPolicyItems;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<AllowPolicyItem> getAllowPolicyItems() {
        return allowPolicyItems;
    }

    public void setAllowPolicyItems(List<AllowPolicyItem> allowPolicyItems) {
        this.allowPolicyItems = allowPolicyItems;
    }

    // Inner class for Resource
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Resource {
        private List<String> databases;
        private String databasesInclusionType;
        private List<String> tables;
        private String tablesInclusionType;
        private List<String> columns;
        private String columnsInclusionType;

        // Getters and Setters
        public List<String> getDatabases() {
            return databases;
        }

        public void setDatabases(List<String> databases) {
            this.databases = databases;
        }

        public String getDatabasesInclusionType() {
            return databasesInclusionType;
        }

        public void setDatabasesInclusionType(String databasesInclusionType) {
            this.databasesInclusionType = databasesInclusionType;
        }

        public List<String> getTables() {
            return tables;
        }

        public void setTables(List<String> tables) {
            this.tables = tables;
        }

        public String getTablesInclusionType() {
            return tablesInclusionType;
        }

        public void setTablesInclusionType(String tablesInclusionType) {
            this.tablesInclusionType = tablesInclusionType;
        }

        public List<String> getColumns() {
            return columns;
        }

        public void setColumns(List<String> columns) {
            this.columns = columns;
        }

        public String getColumnsInclusionType() {
            return columnsInclusionType;
        }

        public void setColumnsInclusionType(String columnsInclusionType) {
            this.columnsInclusionType = columnsInclusionType;
        }
    }

    // Inner class for AllowPolicyItem
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AllowPolicyItem {
        private List<String> users;
        private List<String> groups;
        private List<String> roles;
        private List<String> accesses;

        // Getters and Setters
        public List<String> getUsers() {
            return users;
        }

        public void setUsers(List<String> users) {
            this.users = users;
        }

        public List<String> getGroups() {
            return groups;
        }

        public void setGroups(List<String> groups) {
            this.groups = groups;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public List<String> getAccesses() {
            return accesses;
        }

        public void setAccesses(List<String> accesses) {
            this.accesses = accesses;
        }
    }
}
