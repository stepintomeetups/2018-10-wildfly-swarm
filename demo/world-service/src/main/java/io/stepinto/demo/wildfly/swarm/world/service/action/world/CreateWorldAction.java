package io.stepinto.demo.wildfly.swarm.world.service.action.world;

import io.stepinto.demo.wildfly.swarm.world.service.entity.World;
import io.stepinto.demo.wildfly.swarm.world.service.service.WorldService;
import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldRequest;
import io.stepinto.demo.wildfly_swarm.dto.world.world.CreateWorldResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldInfoType;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldType;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import lombok.extern.jbosslog.JBossLog;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Világok létrehozását kezelő akció osztály.
 */
@JBossLog
@RequestScoped
public class CreateWorldAction extends BaseAction {

    @Inject
    private WorldService worldService;

    /**
     * Világ létrehozása.
     *
     * @param createWorldRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    @Transactional
    public CreateWorldResponse createWorld(CreateWorldRequest createWorldRequest) throws BaseException {
        if (Objects.isNull(createWorldRequest)) {
            throw new BaseException("createWorldRequest is NULL");
        }
        WorldType worldType = createWorldRequest.getWorld();

        World entity = new World();
        entity.setName(worldType.getName());
        World savedWorld = worldService.save(entity);

        CreateWorldResponse response = new CreateWorldResponse();
        WorldInfoType worldInfoType = new WorldInfoType();
        worldInfoType.setId(savedWorld.getId());
        worldInfoType.setName(savedWorld.getName());
        response.setWorld(worldInfoType);
        succesResponse(response);
        return response;
    }

}
