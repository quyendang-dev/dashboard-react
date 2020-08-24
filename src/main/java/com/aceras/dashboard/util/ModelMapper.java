package com.aceras.dashboard.util;

import com.aceras.dashboard.model.Product;
import com.aceras.dashboard.payload.ProductResponse;

public class ModelMapper {

    public static ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setCode(product.getCode());
        productResponse.setName(product.getName());
        productResponse.setType(product.getType());
        productResponse.setDescription(product.getDescription());
        productResponse.setStatus(product.getStatus());
        return productResponse;
    }
}
