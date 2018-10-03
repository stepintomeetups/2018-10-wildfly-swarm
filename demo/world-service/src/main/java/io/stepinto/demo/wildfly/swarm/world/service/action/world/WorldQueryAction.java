package io.stepinto.demo.wildfly.swarm.world.service.action.world;

import io.stepinto.demo.wildfly.swarm.world.service.entity.World;
import io.stepinto.demo.wildfly.swarm.world.service.service.WorldService;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldInfoType;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldListResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Világok listázását kezelő akció osztály.
 */
@RequestScoped
public class WorldQueryAction extends BaseAction {

    @Inject
    private WorldService worldService;

    /**
     * Világ keresése azonosító alapján.
     *
     * @param id világ azonosítója.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public WorldResponse findById(String id) throws BaseException {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id is NULL");
        }
        World world = worldService.findById(id, World.class);

        WorldResponse response = new WorldResponse();
        response.setWorld(toInfoType(world));
        succesResponse(response);
        return response;
    }

    /**
     * Minden világ entitás listázása.
     *
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public WorldListResponse findAll() throws BaseException {
        List<World> worldList = worldService.findAll(World.class);

        List<WorldInfoType> worldInfoTypes = worldList.stream().map(this::toInfoType).collect(Collectors.toList());
        WorldListResponse response = new WorldListResponse();
        response.getList().addAll(worldInfoTypes);
        succesResponse(response);
        return response;
    }

    private WorldInfoType toInfoType(World world) {
        WorldInfoType placeInfoType = new WorldInfoType();
        placeInfoType.setId(world.getId());
        placeInfoType.setName(world.getName());
        return placeInfoType;
    }
}
