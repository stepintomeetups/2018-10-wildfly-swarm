package io.stepinto.demo.wildfly.swarm.world.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldRequest;
import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldListResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
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

@Api("Világok kezelése")
@Path("/worldService/world")
public interface WorldResource {

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Világ lekérdezése azonosító alapján.", response = WorldResponse.class)
    WorldResponse getById(@ApiParam(value = "id", required = true) @PathParam("id") String id) throws BaseException;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő világok lekédezése.", response = WorldListResponse.class)
    WorldListResponse findAll() throws BaseException;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Világ létrehozása kérés objektum alapján.", response = CreateWorldResponse.class)
    CreateWorldResponse createWorld(CreateWorldRequest createWorldRequest) throws BaseException;

}
