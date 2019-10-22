package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.Product;
import edu.unapec.shoppingorders.repositories.ProductRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;

public class ProductRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Product, Long> implements ProductRepository {

    public ProductRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Product.class);
    }
}
