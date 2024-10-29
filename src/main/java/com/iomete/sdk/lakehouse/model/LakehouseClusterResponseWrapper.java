package com.iomete.sdk.lakehouse.models;

import com.iomete.sdk.lakehouse.model.LakehouseClusterResponse;

import java.util.List;

public class LakehouseClusterResponseWrapper {
    private List<LakehouseClusterResponse> items;

    // Getters and Setters
    public List<LakehouseClusterResponse> getItems() {
        return items;
    }

    public void setItems(List<LakehouseClusterResponse> items) {
        this.items = items;
    }
}
