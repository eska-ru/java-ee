package ru.writeway.rest;

import ru.writeway.persist.Category;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Category> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Category getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(Category category);
}

