package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.ReportOrderUser;
import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.repositories.ShoppingOrderRepository;
import edu.unapec.shoppingorders.services.ShoppingOrderService;

import java.util.List;

public class ShoppingOrderServiceImpl extends BaseService<ShoppingOrder, Long> implements ShoppingOrderService {
    public ShoppingOrderServiceImpl(ShoppingOrderRepository repository) {
        super(repository);
    }

    @Override
    public List<ReportOrderUser> reportOrdersUser() {
        List<ReportOrderUser> users = ((ShoppingOrderRepository) repository).reportOrdersUser();
        return users;
    }

    @Override
    public List<ShoppingOrder> getShoppingOrderByUseId(Long id) {
        return ((ShoppingOrderRepository) repository).getShoppingOrderByUseId(id);
    }
}
