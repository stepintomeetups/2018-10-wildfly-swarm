package io.stepinto.demo.wildfly.swarm.world.service.rest;

import io.stepinto.demo.wildfly.swarm.world.service.action.world.CreateWorldAction;
import io.stepinto.demo.wildfly.swarm.world.service.action.world.WorldQueryAction;
import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldRequest;
import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldListResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
public class WorldResourceImpl implements WorldResource {

    @Inject
    private CreateWorldAction createWorldAction;

    @Inject
    private WorldQueryAction worldQueryAction;

    @Override
    public WorldResponse getById(String id) throws BaseException {
        log.tracev(">> getById(id:[{0}])", id);
        try {
            return worldQueryAction.findById(id);
        } finally {
            log.tracev("<< getById(id:[{0}])", id);

        }
    }

    @Override
    public WorldListResponse findAll() throws BaseException {
        log.tracev(">> findAll()");
        try {
            return worldQueryAction.findAll();
        } finally {
            log.tracev("<< findAll()");
        }
    }

    @Override
    public CreateWorldResponse createWorld(CreateWorldRequest createWorldRequest) throws BaseException {
        log.tracev(">> createWorld(createWorldRequest:[{0}])", createWorldRequest);
        try {
            return createWorldAction.createWorld(createWorldRequest);
        } finally {
            log.tracev("<< createWorld(createWorldRequest:[{0}])", createWorldRequest);
        }
    }
}
