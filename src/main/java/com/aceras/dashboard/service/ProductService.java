package com.aceras.dashboard.service;

import com.aceras.dashboard.model.Product;
import com.aceras.dashboard.payload.ProductResponse;
import com.aceras.dashboard.payload.ProductSearchResponse;
import com.aceras.dashboard.payload.StatisticResponse;
import com.aceras.dashboard.repository.ProductRepository;
import com.aceras.dashboard.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public ProductSearchResponse filterProductByCodeAndNameAndDescriptionAndStatus(String code, String name, String description, String status, String type) {
        List<Product> productList = productRepository.findProductByCodeContainsAndNameContainsAndDescriptionContainsAndStatusContainsAndTypeContains(code, name, description, status, type);

        List<ProductResponse> productResponses = productList.stream().map(product->ModelMapper.mapProductToProductResponse(product)).collect(Collectors.toList());

        Long numberOfNewArrival = productResponses.stream().filter(productResponse -> productResponse.getType().equals("New arrival")).count();
        Long numberOfOutOfStock = productResponses.stream().filter(productResponse -> productResponse.getType().equals("Out of stock")).count();
        Long numberOfOldFashion = productResponses.stream().filter(productResponse -> productResponse.getType().equals("Old fashion")).count();
        List<StatisticResponse> statisticResponses = new ArrayList<>();
        statisticResponses.add(new StatisticResponse("New arrival", numberOfNewArrival));
        statisticResponses.add(new StatisticResponse("Out of stock", numberOfOutOfStock));
        statisticResponses.add(new StatisticResponse("Old fashion", numberOfOldFashion));
        ProductSearchResponse response = new ProductSearchResponse(productResponses, statisticResponses);
        return response;

    }

}
