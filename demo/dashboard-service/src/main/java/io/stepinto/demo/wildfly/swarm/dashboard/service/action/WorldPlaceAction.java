package io.stepinto.demo.wildfly.swarm.dashboard.service.action;

import com.netflix.ribbon.RibbonRequest;
import io.netty.buffer.ByteBuf;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.HostRibbonService;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.PlaceRibbonService;
import io.stepinto.demo.wildfly.swarm.dashboard.service.ribbon.service.WorldRibbonService;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.HostType;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.PlaceHostType;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.PlaceType;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceHostType;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceResponse;
import io.stepinto.demo.wildfly_swarm.dto.dashboard.dashboard.WorldPlaceType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostInfoType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostListResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceInfoType;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceListResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Világok kezelésére szolgáló akció osztály.
 */
@RequestScoped
public class WorldPlaceAction extends BaseAction {

    @Inject
    private WorldRibbonService worldRibbonService;

    @Inject
    private PlaceRibbonService placeRibbonService;

    @Inject
    private HostRibbonService hostRibbonService;

    /**
     * Világ helyeinek listázása.
     *
     * @param worldId világ azonosítója.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public WorldPlaceResponse getPlacesByWorldId(String worldId) throws BaseException {
        if (StringUtils.isBlank(worldId)) {
            throw new BaseException("worldId is NULL");
        }

        WorldResponse worldResponse = unwrapRequest(worldRibbonService.getWorldById(worldId), WorldResponse.class);
        PlaceListResponse placeListResponse = unwrapRequest(placeRibbonService.findByWorldId(worldId), PlaceListResponse.class);
        List<PlaceInfoType> placeInfoTypeList = placeListResponse.getList();

        WorldPlaceResponse response = new WorldPlaceResponse();
        WorldPlaceType worldPlace = new WorldPlaceType();
        worldPlace.setWorldName(worldResponse.getWorld().getName());
        List<PlaceType> placeTypeList = placeInfoTypeList.stream() //
            .map(place -> new PlaceType().withId(place.getId())//
                .withPlaceName(place.getName()) //
                .withSector(place.getSector()))
            .collect(Collectors.toList());
        worldPlace.getPlaceList().addAll(placeTypeList);
        response.setWorldPlaces(worldPlace);
        succesResponse(response);
        return response;
    }

    /**
     * Gazdák listázása világokon belül.
     *
     * @param worldId világ azonosítója.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public WorldPlaceHostResponse getHostsByWorldAndPlaceId(String worldId) throws BaseException {
        if (StringUtils.isAnyBlank(worldId)) {
            throw new BaseException("worldId are NULL");
        }

        WorldResponse worldResponse = unwrapRequest(worldRibbonService.getWorldById(worldId), WorldResponse.class);
        PlaceListResponse placeListResponse = unwrapRequest(placeRibbonService.findByWorldId(worldId), PlaceListResponse.class);
        List<PlaceInfoType> placeInfoTypeList = placeListResponse.getList();

        Map<String, List<HostInfoType>> hostMap = new HashMap<>();
        for (PlaceInfoType placeInfoType : placeInfoTypeList) {
            RibbonRequest<ByteBuf> hostList = hostRibbonService.getByWorldAndPlace(worldId, placeInfoType.getId());
            HostListResponse hostListResponse = unwrapRequest(hostList, HostListResponse.class);
            hostMap.put(placeInfoType.getId(), hostListResponse.getList());
        }

        WorldPlaceHostResponse response = new WorldPlaceHostResponse();

        WorldPlaceHostType worldPlace = new WorldPlaceHostType();
        worldPlace.setWorldName(worldResponse.getWorld().getName());
        List<PlaceHostType> placeHostTypes = placeInfoTypeList.stream() //
            .map(place -> new PlaceHostType().withId(place.getId()) //
                .withPlaceName(place.getName()) //
                .withSector(place.getSector()) //
                .withHostList(hostMap.get(place.getId())
                    .stream()
                    .map(hostInfoType -> new HostType().withId(hostInfoType.getId()) //
                        .withName(hostInfoType.getName())
                        .withOperationSystem(hostInfoType.getOperationSystem().name())) //
                    .collect(Collectors.toList()))) //
            .collect(Collectors.toList());
        worldPlace.getPlaceList().addAll(placeHostTypes);

        response.setWorldPlaceHosts(worldPlace);
        succesResponse(response);
        return response;
    }

}
