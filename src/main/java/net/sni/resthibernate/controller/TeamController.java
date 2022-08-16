package net.sni.resthibernate.controller;

import net.sni.resthibernate.service.TeamService;
import net.sni.resthibernate.entity.Team;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/team")
public class TeamController {

    @Inject
    private TeamService teamService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") Long id) {
        final Optional<Team> team = teamService.findOneById(id);

        if (team.isPresent()) {
            return Response.ok(team.get(), MediaType.APPLICATION_JSON).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final Set<Team> teamSet = teamService.findAll();
        return Response.ok(teamSet, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Team entity) {

        if (entity == null || entity.getTeamName() == null) {
            return Response.status(400).build();
        }

        teamService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/{updateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("updateId") Long id, Team entity) {

        if (id == null) {
            return Response.status(400).build();
        }

        if (entity == null || entity.getTeamName() == null) {
            return Response.status(400).build();
        }

        entity.setId(id);
        teamService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Team entity) {
        teamService.delete(entity);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{deleteId}")
    public Response deleteById(@PathParam("deleteId") Long id) {
        teamService.deleteById(id);
        return Response.status(200).build();
    }

    @POST
    @Path("/{teamId}/project/{projectId}")
    public Response addProjectToEmployee(@PathParam("teamId") Long teamId, @PathParam("projectId") Long projectId) {
        final boolean isOk = teamService.addProjectToTeam(teamId, projectId);

        if (isOk) {
            return Response.status(200).build();
        }

        return Response.status(500).build();
    }
}