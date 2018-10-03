package io.stepinto.demo.wildfly.swarm.world.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceRequest;
import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceListResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceResponse;
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

@Api("Világokon belüli helyek kezelése")
@Path("/worldService/place")
public interface PlaceResource {

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Hely lekérdezése azonosító alapján.", response = PlaceResponse.class)
    PlaceResponse getById(@ApiParam(value = "id", required = true) @PathParam("id") String id) throws BaseException;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő helyek lekédezése.", response = PlaceListResponse.class)
    PlaceListResponse findAll() throws BaseException;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Hely létrehozása kérés objektum alapján.", response = CreatePlaceResponse.class)
    CreatePlaceResponse createPlace(CreatePlaceRequest createPlaceRequest) throws BaseException;

    @GET
    @Path("/byWorldId/{worldId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő helyek lekédezése.", response = PlaceListResponse.class)
    PlaceListResponse findByWorldId(@PathParam("worldId") String worldId) throws BaseException;
}
