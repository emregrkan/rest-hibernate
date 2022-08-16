package net.sni.resthibernate.controller;

import net.sni.resthibernate.entity.Project;
import net.sni.resthibernate.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/project")
public class ProjectController {

    @Inject
    private ProjectService projectService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") Long id) {
        final Optional<Project> project = projectService.findOneById(id);

        if (project.isPresent()) {
            return Response.ok(project.get(), MediaType.APPLICATION_JSON).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final Set<Project> projectSet = projectService.findAll();
        return Response.ok(projectSet, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Project entity) {

        if (entity == null || entity.getName() == null) {
            return Response.status(400).build();
        }

        projectService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/{updateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("updateId") Long id, Project entity) {

        if (id == null) {
            return Response.status(400).build();
        }

        if (entity == null || entity.getName() == null) {
            return Response.status(400).build();
        }

        entity.setId(id);
        projectService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Project entity) {
        projectService.delete(entity);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{deleteId}")
    public Response deleteById(@PathParam("deleteId") Long id) {
        projectService.deleteById(id);
        return Response.status(200).build();
    }
}

