package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.dtos.ProductDto;
import edu.unapec.shoppingorders.models.Product;
import edu.unapec.shoppingorders.services.ProductService;

import javax.ws.rs.Path;

@Path("/products")
public class ProductController extends BaseController<Product, ProductDto, ProductService> {
    public ProductController() {
        super(Product.class, ProductDto.class, ProductService.class);
    }
}
