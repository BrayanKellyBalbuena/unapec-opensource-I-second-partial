package com.brayan.shoppingorders.infrastruture.services;

import com.brayan.shoppingorders.core.interfaces.Service;
import com.brayan.shoppingorders.core.models.ReportOrderDate;
import com.brayan.shoppingorders.core.models.ReportOrderUser;
import com.brayan.shoppingorders.core.models.ShoppingOrder;

import java.util.List;

public interface ShoppingOrderService extends Service<ShoppingOrder, Long> {
     List<ReportOrderUser> reportOrdersUser();

    List<ReportOrderDate> reportOrdersByDate();
     List<ShoppingOrder> getShoppingOrderByUseId(Long id);
}
