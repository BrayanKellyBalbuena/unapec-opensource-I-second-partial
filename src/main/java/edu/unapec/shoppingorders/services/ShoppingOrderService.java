package edu.unapec.shoppingorders.services;

import edu.unapec.shoppingorders.models.ReportOrderDate;
import edu.unapec.shoppingorders.models.ReportOrderUser;
import edu.unapec.shoppingorders.models.ShoppingOrder;

import java.util.List;

public interface ShoppingOrderService extends Service<ShoppingOrder, Long> {
     List<ReportOrderUser> reportOrdersUser();

    List<ReportOrderDate> reportOrdersByDate();
     List<ShoppingOrder> getShoppingOrderByUseId(Long id);
}
