package com.iomete.sdk.iam.userRoleGroupMappings.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse {

    private Item item;
    private Permissions permissions;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static class Item {
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private String origin;
        private String addedBy;
        private String addedAt;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getAddedBy() {
            return addedBy;
        }

        public void setAddedBy(String addedBy) {
            this.addedBy = addedBy;
        }

        public String getAddedAt() {
            return addedAt;
        }

        public void setAddedAt(String addedAt) {
            this.addedAt = addedAt;
        }
    }

    public static class Permissions {
        private boolean canManage;

        // Getters and Setters

        public boolean isCanManage() {
            return canManage;
        }

        public void setCanManage(boolean canManage) {
            this.canManage = canManage;
        }
    }
}

