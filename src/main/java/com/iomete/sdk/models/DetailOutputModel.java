package com.iomete.sdk.models;

/**
 * Model representing detailed output with an item and its permissions.
 */
public class DetailOutputModel {
    /**
     * The detailed item.
     */
    private Object item;

    /**
     * The permissions associated with the item.
     */
    private ResourcePermissionsModel permissions;

    public DetailOutputModel(Object item, ResourcePermissionsModel permissions) {
        this.item = item;
        this.permissions = permissions;
    }

    // Getters and Setters
    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public ResourcePermissionsModel getPermissions() {
        return permissions;
    }

    public void setPermissions(ResourcePermissionsModel permissions) {
        this.permissions = permissions;
    }
}

