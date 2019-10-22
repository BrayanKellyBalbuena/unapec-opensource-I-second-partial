package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.ProductCategory;
import edu.unapec.shoppingorders.repositories.ProductCategoryRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCategoryRepositoryDatabaseImpl extends RepositoryDatabaseImpl<ProductCategory, Long>
        implements ProductCategoryRepository {

    public ProductCategoryRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, ProductCategory.class);
    }
}
