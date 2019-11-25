package com.brayan.shoppingorders.infrastruture.repositories.impl.database;

import com.brayan.shoppingorders.core.models.ProductCategory;
import com.brayan.shoppingorders.infrastruture.repositories.ProductCategoryRepository;
import com.brayan.shoppingorders.infrastruture.utils.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductCategoryRepositoryDatabaseImpl extends RepositoryDatabaseImpl<ProductCategory, Long>
        implements ProductCategoryRepository {

    public ProductCategoryRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, ProductCategory.class);
    }
}
