package io.stepinto.demo.wildfly.swarm.story.service.rest;

import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryRequest;
import io.stepinto.demo.wildfly_swarm.dto.story.story.CreateStoryResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryListResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryResponse;
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

@Api("Történet szálakat kezelő végpont")
@Path("/storyService/story")
public interface StoryResource {

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Történetszál lekérdezése azonosító alapján.", response = StoryResponse.class)
    StoryResponse getById(@ApiParam(value = "id", required = true) @PathParam("id") String id) throws BaseException;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Rendszerben lévő történetszálak lekédezése.", response = StoryListResponse.class)
    StoryListResponse findAll() throws BaseException;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Történetszál létrehozása kérés objektum alapján.", response = CreateStoryResponse.class)
    CreateStoryResponse createStory(CreateStoryRequest createStoryRequest) throws BaseException;

}
