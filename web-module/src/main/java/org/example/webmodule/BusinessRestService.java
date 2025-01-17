package org.example.webmodule;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.businessmodule.BusinessServiceRemote;
import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;
import org.example.webmodule.jndi.JNDIModule;
import org.wildfly.security.http.oidc.OidcPrincipal;

import java.security.Principal;
import java.util.List;

@Path("")
public class BusinessRestService {
    @Context
    private HttpServletRequest httpRequest;

    private final BusinessServiceRemote businessService;

    public BusinessRestService() {
        this.businessService = JNDIModule.getBusinessService();
    }


    @GET
    @Path("qwe")
    @RolesAllowed("user")
    @Produces("application/json")
    public Response getQwe() {
        return Response.ok("{\"message\": \"2 service\"}").build(); // Возвращаем корректный JSON
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response processBusinessLogic() {
        System.out.println("processBusinessLogic beginning");
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();

            System.out.println(token);
            List<EventRead> list = businessService.getAll(token);
            return Response.ok(list).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("id") String id) {
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();
            System.out.println("Token: " + token);

            return Response.ok(businessService.getById(token, id)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(@Valid EventWrite dto) {
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();
            System.out.println("Token: " + token);

            Object event = businessService.save(token, dto);
            return Response.status(201).entity(event).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sell/vip/{ticket_id}/{person_id}")
    public Response copyTicketWithDoublePriceAndVip(@PathParam("ticket_id") String ticket_id, @PathParam("person_id") String person_id) {
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();
            System.out.println("Token: " + token);

            Object e = businessService.copyTicketWithDoublePriceAndVip(token, ticket_id, person_id);
            return Response.ok(e).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("event/{event_id}/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEvent(@PathParam("event_id") String event_id) {
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();
            System.out.println("Token: " + token);

            businessService.delete(token, event_id);
            return Response.status(204).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}