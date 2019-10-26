package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.dtos.OrderDto;
import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.services.ShoppingOrderService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;


@Path("/shopping-orders")
public class ShoppingOrderController extends BaseController<ShoppingOrder, OrderDto, ShoppingOrderService> {
    public ShoppingOrderController() {
        super(ShoppingOrder.class, OrderDto.class, ShoppingOrderService.class);
    }

    @Path("report-orders-user")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportOrder() {
        try {
            return Response.ok(service.reportOrdersUser()).build();
        } catch (Exception ex) {
            return Response.serverError().entity(ex).build();
        }
    }

    @Path("/user/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOredersByUsersId(@PathParam("id") Long id) {
        try {
            return Response.ok(service.getShoppingOrderByUseId(id)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(ex).build();
        }

    }
}
