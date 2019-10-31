package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.dtos.UserDto;
import edu.unapec.shoppingorders.dtos.UserLoginDto;
import edu.unapec.shoppingorders.models.User;
import edu.unapec.shoppingorders.services.UserService;
import edu.unapec.shoppingorders.utils.HashUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Path("/users")
public class UsersController extends BaseController<User, UserDto, UserService> {
    public UsersController() {
        super(User.class, UserDto.class, UserService.class);
    }

    @Override
    public Response post(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        try {
            Long id = service.save(user);
            return Response.created(new URI("/api/users/" + id)).build();
        } catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
            return Response.serverError().entity(e).build();
        } catch (Exception ex) {
            return Response.serverError().entity(ex).build();
        }

    }

    @Path("/login")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login(UserLoginDto dto) {
        try {
            Optional<User> result = service.login(modelMapper.map(dto, User.class));
            if (result.isPresent()) {
                return Response.ok(result.get()).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (NoSuchAlgorithmException e) {
            return Response.serverError().entity(e).build();
        }
    }
}
