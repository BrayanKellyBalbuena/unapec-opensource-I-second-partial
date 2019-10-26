package edu.unapec.shoppingorders.repositories;

import edu.unapec.shoppingorders.models.ReportOrderUser;
import edu.unapec.shoppingorders.models.ShoppingOrder;

import java.util.List;

public interface ShoppingOrderRepository extends Repository<ShoppingOrder, Long> {
     List<ReportOrderUser> reportOrdersUser();

     List<ShoppingOrder> getShoppingOrderByUseId(Long id);
}
