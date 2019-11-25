package com.brayan.shoppingorders.web.controllers;

import com.brayan.shoppingorders.core.models.Location;
import com.brayan.shoppingorders.infrastruture.dtos.LocationDto;
import com.brayan.shoppingorders.infrastruture.services.LocationService;

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
