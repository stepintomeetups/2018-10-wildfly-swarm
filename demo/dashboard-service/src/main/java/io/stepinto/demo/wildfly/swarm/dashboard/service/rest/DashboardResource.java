package io.stepinto.demo.wildfly.swarm.dashboard.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.UpgradeHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api("Vezérlő teremben lévő kezelőfelület szolgáltatása")
@Path("/dashboard")
public interface DashboardResource {

    @GET
    @Path("/placesInWorld/{worldId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Világokon belüli helyek lekérdezése világ azonosító alapján.", response = WorldPlaceResponse.class)
    WorldPlaceResponse getPlacesInWorld(@PathParam("worldId") String worldId) throws BaseException;

    @GET
    @Path("/hostsInWorld/{worldId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Világokon belüli helyek és gadzák lekérdezése világ azonosító alapján.", response = WorldPlaceResponse.class)
    WorldPlaceHostResponse getPlacesAndHostsInWorld(@PathParam("worldId") String worldId) throws BaseException;

    @GET
    @Path("/upgradeOs/{hostId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Gazda renszerének következő verzióra való frissítése gazda azonosító alapján.", response = WorldPlaceResponse.class)
    UpgradeHostResponse upgrade(@PathParam("hostId") String hostId) throws BaseException;
}
