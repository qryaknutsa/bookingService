package org.example.webmodule;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.businessmodule.BusinessServiceRemote;
import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;

import java.util.List;

@Path("")
public class BusinessRestService {

    @EJB
    private BusinessServiceRemote businessService;

    @GET
    @Path("qwe")
    @Produces("application/json")
    public Response getQwe() {
        return Response.ok("{\"message\": \"qwe\"}").build(); // Возвращаем корректный JSON
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response processBusinessLogic() {
        return Response.ok(businessService.getAll()).build();
    }

    @GET
    @Path("event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("id") String id) {
        return Response.ok(businessService.getById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(@Valid EventWrite dto) {
        Object event = businessService.save(dto);
        return Response.status(201).entity(event).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sell/vip/{ticket_id}/{person_id}")
    public Response copyTicketWithDoublePriceAndVip(@PathParam("ticket_id") String ticket_id, @PathParam("person_id") String person_id) {
        Object e = businessService.copyTicketWithDoublePriceAndVip(ticket_id, person_id);
        return Response.ok(e).build();
    }

    @DELETE
    @Path("event/{event_id}/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEvent(@PathParam("event_id") String event_id) {
        businessService.delete(event_id);
        return Response.status(204).build();
    }
}