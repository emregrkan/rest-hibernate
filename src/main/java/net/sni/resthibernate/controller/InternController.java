package net.sni.resthibernate.controller;


import net.sni.resthibernate.entity.Intern;
import net.sni.resthibernate.service.InternService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/intern")
public class InternController {

    @Inject
    private InternService internService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") Long id) {
        final Optional<Intern> intern = internService.findOneById(id);

        if (intern.isPresent()) {
            return Response.ok(intern.get(), MediaType.APPLICATION_JSON).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final Set<Intern> internSet = internService.findAll();
        return Response.ok(internSet, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Intern entity) {

        if (entity == null) {
            return Response.status(400).build();
        }

        internService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/{updateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("updateId") Long id, Intern entity) {

        if (id == null || entity == null) {
            return Response.status(400).build();
        }

        entity.setId(id);
        internService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Intern entity) {
        internService.delete(entity);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{deleteId}")
    public Response deleteById(@PathParam("deleteId") Long id) {
        internService.deleteById(id);
        return Response.status(200).build();
    }
}
