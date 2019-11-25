package com.brayan.shoppingorders.infrastruture.services.impl;

import com.brayan.shoppingorders.core.interfaces.Repository;
import com.brayan.shoppingorders.core.models.Product;
import com.brayan.shoppingorders.infrastruture.services.ProductService;

public class ProductServiceImpl extends BaseService<Product, Long> implements ProductService {
    public ProductServiceImpl(Repository<Product, Long> repository) {
        super(repository);
    }
}
