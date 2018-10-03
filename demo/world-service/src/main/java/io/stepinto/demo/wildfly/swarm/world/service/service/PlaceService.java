package io.stepinto.demo.wildfly.swarm.world.service.service;

import io.stepinto.demo.wildfly.swarm.world.service.entity.Place;
import io.stepinto.demo.wildfly.swarm.world.service.repository.PlaceRepository;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import io.stepinto.wildfly.swarm.demo.common.service.BaseService;
import lombok.extern.jbosslog.JBossLog;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * {@link Place} entitást kezelő szolgáltatás.
 */
@JBossLog
@RequestScoped
public class PlaceService extends BaseService<Place> {

    @Inject
    private PlaceRepository placeRepository;

    /**
     * Hely entitások listázása világ azonosító alapján.
     *
     * @param worldId világ azonosító.
     * @return entitás lista.
     */
    public List<Place> findByWorldId(String worldId) throws BaseException {
        log.tracev(">> findByWorldId(worldId:[{0}])", worldId);
        if (StringUtils.isBlank(worldId)) {
            throw new BaseException("id is NULL");
        }
        List<Place> placeList;
        try {
            placeList = placeRepository.findByWorldId(worldId);
        } finally {
            log.tracev("<< findByWorldId(worldId:[{0}])", worldId);
        }
        return placeList;
    }

}
