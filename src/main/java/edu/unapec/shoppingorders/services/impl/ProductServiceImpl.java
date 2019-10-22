package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.Product;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.services.ProductService;

public class ProductServiceImpl extends BaseService<Product, Long> implements ProductService {
    public ProductServiceImpl(Repository<Product, Long> repository) {
        super(repository);
    }
}
