package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.dtos.OrderDto;
import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.services.ShoppingOrderService;

import javax.ws.rs.Path;


@Path("shopping-orders")
public class ShoppingOrderController extends BaseController<ShoppingOrder, OrderDto, ShoppingOrderService> {
    public ShoppingOrderController() {
        super(ShoppingOrder.class, OrderDto.class, ShoppingOrderService.class);
    }
}
