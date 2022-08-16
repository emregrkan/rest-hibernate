package net.sni.resthibernate.controller;

import net.sni.resthibernate.entity.Employee;
import net.sni.resthibernate.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/employee")
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") Long id) {
        final Optional<Employee> employee = employeeService.findOneById(id);

        if (employee.isPresent()) {
            return Response.ok(employee.get(), MediaType.APPLICATION_JSON).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final Set<Employee> employeeSet = employeeService.findAll();
        return Response.ok(employeeSet, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Employee entity) {

        if (entity == null) {
            return Response.status(400).build();
        }

        employeeService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/{updateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("updateId") Long id, Employee entity) {

        if (id == null || entity == null) {
            return Response.status(400).build();
        }

        entity.setId(id);
        employeeService.saveOrUpdate(entity);

        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Employee entity) {
        employeeService.delete(entity);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{deleteId}")
    public Response deleteById(@PathParam("deleteId") Long id) {
        employeeService.deleteById(id);
        return Response.status(200).build();
    }

    @POST
    @Path("/{employeeId}/project/{projectId}")
    public Response addProjectToEmployee(@PathParam("employeeId") Long employeeId, @PathParam("projectId") Long projectId) {
        final boolean isOk = employeeService.addProjectToEmployee(employeeId, projectId);

        if (isOk) {
            return Response.status(200).build();
        }

        return Response.status(500).build();
    }
}
