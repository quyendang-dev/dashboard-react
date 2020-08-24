package com.aceras.dashboard.controller;

import com.aceras.dashboard.payload.ProductSearchResponse;
import com.aceras.dashboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/api/products", params = {"code","name","description","status","type"})
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ProductSearchResponse getProducts(@RequestParam @DefaultValue("") String code,
                                             @RequestParam @DefaultValue("") String name,
                                             @RequestParam @DefaultValue("") String description,
                                             @RequestParam @DefaultValue("") String status,
                                             @RequestParam @DefaultValue("") String type) {
        return productService.filterProductByCodeAndNameAndDescriptionAndStatus(code, name, description, status, type);
    }
}
