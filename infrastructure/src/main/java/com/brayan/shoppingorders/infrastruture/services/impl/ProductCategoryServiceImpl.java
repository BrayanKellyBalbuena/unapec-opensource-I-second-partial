package com.brayan.shoppingorders.infrastruture.services.impl;

import com.brayan.shoppingorders.core.interfaces.Repository;
import com.brayan.shoppingorders.core.models.ProductCategory;
import com.brayan.shoppingorders.infrastruture.services.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl extends BaseService<ProductCategory, Long>
        implements ProductCategoryService {

    public ProductCategoryServiceImpl(Repository<ProductCategory, Long> repository) {
        super(repository);
    }
}
