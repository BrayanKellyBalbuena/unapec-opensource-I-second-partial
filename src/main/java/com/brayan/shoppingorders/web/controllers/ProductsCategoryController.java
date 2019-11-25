package com.brayan.shoppingorders.web.controllers;

import com.brayan.shoppingorders.core.models.ProductCategory;
import com.brayan.shoppingorders.infrastruture.dtos.ProductCategoryDto;
import com.brayan.shoppingorders.infrastruture.services.ProductCategoryService;

import javax.ws.rs.*;

@Path("/products-category")
public class ProductsCategoryController extends BaseController<ProductCategory,
        ProductCategoryDto, ProductCategoryService> {

    public ProductsCategoryController() {
        super(ProductCategory.class, ProductCategoryDto.class, ProductCategoryService.class);
    }

}
