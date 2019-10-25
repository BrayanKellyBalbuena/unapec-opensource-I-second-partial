package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.services.ShoppingOrderService;

public class ShoppingOrderServiceImpl extends BaseService<ShoppingOrder, Long> implements ShoppingOrderService {
    public ShoppingOrderServiceImpl(Repository<ShoppingOrder, Long> repository) {
        super(repository);
    }
}
