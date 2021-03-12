package ru.writeway.rest;

import ru.writeway.service.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr getById(@PathParam("id") Long id);

    @GET
    @Path("/byCategory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> getAllByCategoryId(@PathParam("id") Long id);

    @GET
    @Path("/byName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> getAllByName(@PathParam("name") String name);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr product);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
