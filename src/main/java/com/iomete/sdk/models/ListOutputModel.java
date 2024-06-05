package com.iomete.sdk.models;

import java.util.List;

public class ListOutputModel {
    private List<?> items;

    public ListOutputModel(List<?> items) {
        this.items = items;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
