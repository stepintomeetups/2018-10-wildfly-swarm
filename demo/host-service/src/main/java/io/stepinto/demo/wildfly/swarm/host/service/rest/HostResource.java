package io.stepinto.demo.wildfly.swarm.host.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostListResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Hosztok műveleteit végző végpont.
 *
 * @author Nandor Holozsnyak
 */
@Api("Gazdák kezelése")
@Path("/hostService/host")
public interface HostResource {

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Gazda lekérdezése azonosító alapján.", response = HostResponse.class)
    HostResponse getById(@ApiParam(value = "id", required = true) @PathParam("id") String id) throws BaseException;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő gazdák lekédezése.", response = HostListResponse.class)
    HostListResponse findAll() throws BaseException;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Gazda létrehozása kérés objektum alapján.", response = CreateHostResponse.class)
    CreateHostResponse createHost(CreateHostRequest createHostRequest) throws BaseException;

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Gazda frissítése kérés objektum alapján.", response = UpdateHostResponse.class)
    UpdateHostResponse updateHost(UpdateHostRequest updateHostRequest) throws BaseException;

    @GET
    @Path("/byWorldAndPlace/{worldId}/{placeId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő gazdák lekédezése világ és hely azonosító alapján.", response = HostListResponse.class)
    HostListResponse findByWorldAndPlaceId(@PathParam("worldId") String worldId, @PathParam("placeId") String placeId) throws BaseException;

}
