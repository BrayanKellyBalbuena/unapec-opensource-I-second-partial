package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.ProductCategory;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.services.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl extends BaseService<ProductCategory, Long>
        implements ProductCategoryService {

    public ProductCategoryServiceImpl(Repository<ProductCategory, Long> repository) {
        super(repository);
    }
}
