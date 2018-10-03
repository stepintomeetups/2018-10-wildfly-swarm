package io.stepinto.demo.wildfly.swarm.world.service.repository;

import io.stepinto.demo.wildfly.swarm.world.service.entity.Place;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends EntityRepository<Place, String> {

    /**
     * Hely entitások listázása világ azonosító alapján.
     *
     * @param worldId világ azonosító.
     * @return entitás lista.
     */
    @Query("SELECT p FROM Place p WHERE p.world.id = ?1")
    List<Place> findByWorldId(String worldId);

}
