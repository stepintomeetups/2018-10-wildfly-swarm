package io.stepinto.demo.wildfly.swarm.host.service.rest;

import io.stepinto.demo.wildfly.swarm.host.service.action.CreateHostAction;
import io.stepinto.demo.wildfly.swarm.host.service.action.HostListAction;
import io.stepinto.demo.wildfly.swarm.host.service.action.UpdateHostAction;
import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.CreateHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostListResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostResponse;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


/**
 *
 */
@JBossLog
@RequestScoped
public class HostResourceImpl implements HostResource {

    @Inject
    private HostListAction hostListAction;

    @Inject
    private CreateHostAction createHostAction;

    @Inject
    private UpdateHostAction updateHostAction;

    @Override
    public HostResponse getById(String id) throws BaseException {
        log.tracev(">> getById(id:[{0}])", id);
        try {
            return hostListAction.findById(id);
        } finally {
            log.tracev("<< getById(id:[{0}])", id);

        }
    }

    @Override
    public HostListResponse findAll() throws BaseException {
        log.tracev(">> findAll()");
        try {
            return hostListAction.findAll();
        } finally {
            log.tracev("<< findAll()");
        }
    }

    @Override
    public CreateHostResponse createHost(CreateHostRequest createHostRequest) throws BaseException {
        log.tracev(">> createHost(createHostRequest:[{0}])", createHostRequest);
        try {
            return createHostAction.createHost(createHostRequest);
        } finally {
            log.tracev("<< createHost(createHostRequest:[{0}])", createHostRequest);
        }
    }

    @Override
    public UpdateHostResponse updateHost(UpdateHostRequest updateHostRequest) throws BaseException {
        log.tracev(">> updateHost(updateHostRequest:[{0}])", updateHostRequest);
        try {
            return updateHostAction.updateHost(updateHostRequest);
        } finally {
            log.tracev("<< updateHost(updateHostRequest:[{0}])", updateHostRequest);
        }
    }

    @Override
    public HostListResponse findByWorldAndPlaceId(String worldId, String placeId) throws BaseException {
        log.tracev(">> findByWorldAndPlaceId(worldId:[{0}], placeId:[{1}])", worldId, placeId);
        try {
            return hostListAction.findByWorldAndPlaceId(worldId, placeId);
        } finally {
            log.tracev("<< findByWorldAndPlaceId(worldId:[{0}], placeId:[{1}])", worldId, placeId);
        }
    }
}

