package io.stepinto.demo.wildfly.swarm.world.service.repository;

import io.stepinto.demo.wildfly.swarm.world.service.entity.World;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface WorldRepository extends EntityRepository<World, String> {
}
