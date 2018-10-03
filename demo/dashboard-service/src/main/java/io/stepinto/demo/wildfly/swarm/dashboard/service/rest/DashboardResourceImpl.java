package io.stepinto.demo.wildfly.swarm.dashboard.service.rest;


import io.stepinto.demo.wildfly.swarm.dashboard.service.action.HostAction;
import io.stepinto.demo.wildfly.swarm.dashboard.service.action.WorldPlaceAction;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.UpgradeHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceResponse;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
public class DashboardResourceImpl implements DashboardResource {

    @Inject
    private WorldPlaceAction worldPlaceAction;

    @Inject
    private HostAction hostAction;

    @Override
    public WorldPlaceResponse getPlacesInWorld(String worldId) throws BaseException {
        log.tracev(">> getPlacesInWorld()");
        WorldPlaceResponse response;
        try {
            response = worldPlaceAction.getPlacesByWorldId(worldId);
        } finally {
            log.tracev("<< getPlacesInWorld()");
        }
        return response;
    }

    @Override
    public WorldPlaceHostResponse getPlacesAndHostsInWorld(String worldId) throws BaseException {
        log.tracev(">> getPlacesAndHostsInWorld()");
        WorldPlaceHostResponse response;
        try {
            response = worldPlaceAction.getHostsByWorldAndPlaceId(worldId);
        } finally {
            log.tracev("<< getPlacesAndHostsInWorld()");
        }
        return response;
    }

    @Override
    public UpgradeHostResponse upgrade(String hostId) throws BaseException {
        log.tracev(">> upgrade()");
        UpgradeHostResponse response;
        try {
            response = hostAction.upgradeHostOs(hostId);
        } finally {
            log.tracev("<< upgrade()");
        }
        return response;
    }
}
