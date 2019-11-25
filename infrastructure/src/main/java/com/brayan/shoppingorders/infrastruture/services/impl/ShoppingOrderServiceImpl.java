package com.brayan.shoppingorders.infrastruture.services.impl;

import com.brayan.shoppingorders.core.models.ReportOrderDate;
import com.brayan.shoppingorders.core.models.ReportOrderUser;
import com.brayan.shoppingorders.core.models.ShoppingOrder;
import com.brayan.shoppingorders.infrastruture.repositories.ShoppingOrderRepository;
import com.brayan.shoppingorders.infrastruture.services.ShoppingOrderService;

import java.util.List;

public class ShoppingOrderServiceImpl extends BaseService<ShoppingOrder, Long> implements ShoppingOrderService {
    public ShoppingOrderServiceImpl(ShoppingOrderRepository repository) {
        super(repository);
    }

    @Override
    public List<ShoppingOrder> getShoppingOrderByUseId(Long id) {
        return ((ShoppingOrderRepository) repository).getShoppingOrderByUseId(id);
    }

    @Override
    public List<ReportOrderUser> reportOrdersUser() {
        List<ReportOrderUser> users = ((ShoppingOrderRepository) repository).reportOrdersUser();
        return users;
    }

    @Override
    public List<ReportOrderDate> reportOrdersByDate() {
        try {
            return ((ShoppingOrderRepository) repository).reportOrdersDate();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
