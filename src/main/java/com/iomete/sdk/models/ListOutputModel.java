package com.iomete.sdk.models;

import java.util.List;

public class ListOutputModel<T> {
    private List<T> items;

    public ListOutputModel(List<T> items) {
        this.items = items;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
