package io.stepinto.demo.wildfly.swarm.host.service.repository;

import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface HostRepository extends EntityRepository<Host, String> {

    /**
     * Gazdák lekérdezése világ és hely azonosító alapján.
     *
     * @param worldId világ azonosító.
     * @param placeId hely azonosító.
     * @return entitás lista.
     */
    @Query("SELECT h FROM Host h WHERE h.worldId = ?1 AND h.placeId = ?2")
    List<Host> findHostListByWorldAndPlaceId(String worldId, String placeId);

}
