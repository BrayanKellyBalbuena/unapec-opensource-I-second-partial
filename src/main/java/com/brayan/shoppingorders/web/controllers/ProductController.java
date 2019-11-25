package com.brayan.shoppingorders.web.controllers;


import com.brayan.shoppingorders.core.models.Product;
import com.brayan.shoppingorders.infrastruture.dtos.ProductDto;
import com.brayan.shoppingorders.infrastruture.services.ProductService;

import javax.ws.rs.Path;

@Path("/products")
public class ProductController extends BaseController<Product, ProductDto, ProductService> {
    public ProductController() {
        super(Product.class, ProductDto.class, ProductService.class);
    }
}
