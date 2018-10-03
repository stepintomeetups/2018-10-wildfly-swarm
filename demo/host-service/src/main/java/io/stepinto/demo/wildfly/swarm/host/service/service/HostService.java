package io.stepinto.demo.wildfly.swarm.host.service.service;

import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import io.stepinto.demo.wildfly.swarm.host.service.repository.HostRepository;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.stepinto.wildfly.swarm.demo.common.service.BaseService;
import lombok.extern.jbosslog.JBossLog;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@JBossLog
@RequestScoped
public class HostService extends BaseService<Host> {

    @Inject
    private HostRepository hostRepository;

    /**
     * Gazdák lekérdezése világ és hely azonosító alapján.
     *
     * @param worldId világ azonosító.
     * @param placeId hely azonosító.
     * @return entitás lista.
     */
    public List<Host> findHostListByWorldAndPlaceId(String worldId, String placeId) throws BaseException {
        log.tracev(">> findHostListByWorldAndPlaceId(worldId:[{0}], placeId:[{1}])", worldId, placeId);
        if (StringUtils.isAnyBlank(worldId, placeId)) {
            throw new BaseException("worldId and/or placeId are NULL");
        }
        List<Host> hostList;
        try {
            hostList = hostRepository.findHostListByWorldAndPlaceId(worldId, placeId);
        } finally {
            log.tracev("<< findHostListByWorldAndPlaceId(worldId:[{0}], placeId:[{1}])", worldId, placeId);
        }
        return hostList;
    }

}
