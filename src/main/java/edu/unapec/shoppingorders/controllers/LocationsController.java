package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.configurations.SpringDIConfiguration;
import edu.unapec.shoppingorders.dtos.LocationDto;
import edu.unapec.shoppingorders.dtos.ProductCategoryDto;
import edu.unapec.shoppingorders.models.Location;
import edu.unapec.shoppingorders.models.ProductCategory;
import edu.unapec.shoppingorders.services.LocationService;
import edu.unapec.shoppingorders.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/locations")
public class LocationsController extends BaseController<Location, LocationDto, LocationService> {
    public LocationsController() {
        super(Location.class, LocationDto.class, LocationService.class);
    }
}
