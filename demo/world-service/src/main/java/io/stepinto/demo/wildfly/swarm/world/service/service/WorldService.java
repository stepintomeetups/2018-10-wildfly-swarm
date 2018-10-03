package io.stepinto.demo.wildfly.swarm.world.service.service;

import io.stepinto.demo.wildfly.swarm.world.service.entity.World;
import io.stepinto.wildfly.swarm.demo.common.service.BaseService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * {@link World} entitást kezelő szolgáltatás.
 *
 */
@RequestScoped
public class WorldService extends BaseService<World> {

    @Inject
    private WorldService worldService;
}
