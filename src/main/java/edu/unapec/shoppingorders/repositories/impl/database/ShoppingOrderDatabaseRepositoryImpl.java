package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.repositories.ShoppingOrderRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;

public class ShoppingOrderDatabaseRepositoryImpl extends RepositoryDatabaseImpl<ShoppingOrder, Long> implements ShoppingOrderRepository {
    public ShoppingOrderDatabaseRepositoryImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, ShoppingOrder.class);
    }
}
