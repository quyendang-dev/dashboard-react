package com.aceras.dashboard.payload;

import java.util.List;

public class ProductSearchResponse {
    private List<ProductResponse> productResponses;
    private List<StatisticResponse> statisticResponses;

    public ProductSearchResponse(List<ProductResponse> productResponses, List<StatisticResponse> statisticResponses) {
        this.productResponses = productResponses;
        this.statisticResponses = statisticResponses;
    }

    public ProductSearchResponse() {
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    public List<StatisticResponse> getStatisticResponses() {
        return statisticResponses;
    }

    public void setStatisticResponses(List<StatisticResponse> statisticResponses) {
        this.statisticResponses = statisticResponses;
    }
}
