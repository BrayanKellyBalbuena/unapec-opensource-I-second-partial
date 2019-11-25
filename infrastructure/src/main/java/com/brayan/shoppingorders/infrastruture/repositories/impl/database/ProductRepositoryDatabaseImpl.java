package com.brayan.shoppingorders.infrastruture.repositories.impl.database;

import com.brayan.shoppingorders.core.models.Product;
import com.brayan.shoppingorders.infrastruture.repositories.ProductRepository;
import com.brayan.shoppingorders.infrastruture.utils.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Product, Long> implements ProductRepository {

    public ProductRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Product.class);
    }
}
