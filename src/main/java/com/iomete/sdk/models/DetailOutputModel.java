package com.iomete.sdk.models;

/**
 * Model representing detailed output with an item and its permissions.
 */
public class DetailOutputModel<T> {
    /**
     * The detailed item.
     */
    private T item;

    /**
     * The permissions associated with the item.
     */
    private ResourcePermissionsModel permissions;

    public DetailOutputModel(T item, ResourcePermissionsModel permissions) {
        this.item = item;
        this.permissions = permissions;
    }

    // Getters and Setters
    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public ResourcePermissionsModel getPermissions() {
        return permissions;
    }

    public void setPermissions(ResourcePermissionsModel permissions) {
        this.permissions = permissions;
    }
}

