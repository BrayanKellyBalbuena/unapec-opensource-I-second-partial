package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.configurations.SpringDIConfiguration;
import edu.unapec.shoppingorders.dtos.BaseDto;
import edu.unapec.shoppingorders.models.BaseModel;
import edu.unapec.shoppingorders.services.Service;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Path("base")
public class BaseController<TModel extends BaseModel, TDto extends BaseDto, TService extends Service> {
    protected ApplicationContext ctx;
    protected TService service;
    protected ModelMapper modelMapper;
    protected Class<TModel> modelClass;
    protected Class<TDto> dtoClass;
    protected Class<TService> serviceClass;

    public BaseController() {

    }

    public BaseController(Class<TModel> modelClass, Class<TDto> dtoClass, Class<TService> serviceClass) {
        this.modelClass = modelClass;
        this.dtoClass = dtoClass;
        ctx = new AnnotationConfigApplicationContext(SpringDIConfiguration.class);
        service = ctx.getBean(serviceClass);
        modelMapper = ctx.getBean(ModelMapper.class);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        try {
            List<TDto> dtos = (List<TDto>) service.getAll().stream().map(s -> modelMapper.map(s, dtoClass))
                    .collect(Collectors.toList());

            return Response.ok(dtos).build();
        } catch (Exception ex) {
            return null;
        }
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response get(@PathParam("id") Long id) {
        try {
            TDto dto = modelMapper.map(service.findById(id), dtoClass);
            return Response.ok(dto).build();
        } catch (Exception ex) {
            return null;
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response post(TDto dto) {
        try {
            service.save(modelMapper.map(dto, modelClass));
            return Response.status(Response.Status.CREATED).entity(dto).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateProductCategory(@PathParam("id") Long id, TDto dto) {
        try {
            if (id != dto.getId()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(dto).build();
            } else {
                service.update(modelMapper.map(dto, modelClass));
            }
            return Response.noContent().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteProductCategory(@PathParam("id") Long id) {
        try {
            service.delete(id);
            return Response.status(Response.Status.ACCEPTED).entity("Delete successfully !!").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
