package com.brayan.shoppingorders.infrastruture.repositories;

import com.brayan.shoppingorders.core.interfaces.Repository;
import com.brayan.shoppingorders.core.models.ReportOrderDate;
import com.brayan.shoppingorders.core.models.ReportOrderUser;
import com.brayan.shoppingorders.core.models.ShoppingOrder;

import java.util.List;

public interface ShoppingOrderRepository extends Repository<ShoppingOrder, Long> {
     List<ReportOrderUser> reportOrdersUser();

    List<ReportOrderDate> reportOrdersDate();
     List<ShoppingOrder> getShoppingOrderByUseId(Long id);
}
