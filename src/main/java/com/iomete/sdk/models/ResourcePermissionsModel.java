package com.iomete.sdk.models;

/**
 * Model representing resource permissions.
 */
public class ResourcePermissionsModel {
    /**
     * Indicates if the resource can be managed.
     */
    private boolean canManage;

    public ResourcePermissionsModel() {
        this.canManage = false;
    }

    public ResourcePermissionsModel(boolean canManage) {
        this.canManage = canManage;
    }

    // Getter and Setter
    public boolean isCanManage() {
        return canManage;
    }

    public void setCanManage(boolean canManage) {
        this.canManage = canManage;
    }
}
