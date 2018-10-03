package io.stepinto.demo.wildfly.swarm.guest.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestRequest;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestListResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api("Vendégek kezelése")
@Path("/guestService/guest")
public interface GuestResource {

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Hely lekérdezése azonosító alapján.", response = GuestResponse.class)
    GuestResponse getById(@ApiParam(value = "id", required = true) @PathParam("id") String id) throws BaseException;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő helyek lekédezése.", response = GuestListResponse.class)
    GuestListResponse findAll() throws BaseException;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Hely létrehozása kérés objektum alapján.", response = CreateGuestResponse.class)
    CreateGuestResponse createGuest(CreateGuestRequest createGuestRequest) throws BaseException;
}
