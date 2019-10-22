package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.configurations.SpringDIConfiguration;
import edu.unapec.shoppingorders.dtos.ProductCategoryDto;
import edu.unapec.shoppingorders.models.ProductCategory;
import edu.unapec.shoppingorders.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import java.util.stream.Collectors;


@Path("/products-category")
public class ProductsCategoryController extends BaseController<ProductCategory, ProductCategoryDto, ProductCategoryService> {

    public ProductsCategoryController() {
        super(ProductCategory.class, ProductCategoryDto.class, ProductCategoryService.class);
    }

}
