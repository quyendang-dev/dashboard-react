package com.aceras.dashboard.payload;

import java.io.Serializable;

public class StatisticResponse implements Serializable{
    private String title;
    private Long quantity;

    public StatisticResponse(String title, Long quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public StatisticResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
