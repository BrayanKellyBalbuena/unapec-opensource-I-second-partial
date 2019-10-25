package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.Product;
import edu.unapec.shoppingorders.repositories.ProductRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Product, Long> implements ProductRepository {

    public ProductRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Product.class);
    }
}
