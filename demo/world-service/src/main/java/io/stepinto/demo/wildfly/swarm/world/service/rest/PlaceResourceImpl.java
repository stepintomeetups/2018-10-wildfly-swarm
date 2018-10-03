package io.stepinto.demo.wildfly.swarm.world.service.rest;

import io.stepinto.demo.wildfly.swarm.world.service.action.place.CreatePlaceAction;
import io.stepinto.demo.wildfly.swarm.world.service.action.place.PlaceQueryAction;
import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceRequest;
import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceListResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
public class PlaceResourceImpl implements PlaceResource {

    @Inject
    private CreatePlaceAction createPlaceAction;

    @Inject
    private PlaceQueryAction placeQueryAction;

    @Override
    public PlaceResponse getById(String id) throws BaseException {
        log.tracev(">> getById(id:[{0}])", id);
        try {
            return placeQueryAction.findById(id);
        } finally {
            log.tracev("<< getById(id:[{0}])", id);

        }
    }

    @Override
    public PlaceListResponse findAll() throws BaseException {
        log.tracev(">> findAll()");
        try {
            return placeQueryAction.findAll();
        } finally {
            log.tracev("<< findAll()");
        }
    }

    @Override
    public CreatePlaceResponse createPlace(CreatePlaceRequest createPlaceRequest) throws BaseException {
        log.tracev(">> createPlace(createPlaceRequest:[{0}])", createPlaceRequest);
        try {
            return createPlaceAction.createPlace(createPlaceRequest);
        } finally {
            log.tracev("<< createPlace(createPlaceRequest:[{0}])", createPlaceRequest);
        }
    }

    @Override
    public PlaceListResponse findByWorldId(String worldId) throws BaseException {
        log.tracev(">> findByWorldId()");
        try {
            return placeQueryAction.findByWorldId(worldId);
        } finally {
            log.tracev("<< findByWorldId()");
        }
    }
}
