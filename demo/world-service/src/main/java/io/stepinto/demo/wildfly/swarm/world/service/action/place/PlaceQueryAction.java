package io.stepinto.demo.wildfly.swarm.world.service.action.place;

import io.stepinto.demo.wildfly.swarm.world.service.entity.Place;
import io.stepinto.demo.wildfly.swarm.world.service.service.PlaceService;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceInfoType;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceListResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helyek listázását kezelő akció osztály.
 */
@RequestScoped
public class PlaceQueryAction extends BaseAction {

    @Inject
    private PlaceService placeService;

    /**
     * Hely lekérdezése.
     *
     * @param id azonosító
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public PlaceResponse findById(String id) throws BaseException {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id is NULL");
        }
        Place place = placeService.findById(id, Place.class);

        PlaceResponse response = new PlaceResponse();
        response.setPlace(toInfoType(place));
        succesResponse(response);
        return response;
    }

    /**
     * Minden hely lekérdezése.
     *
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public PlaceListResponse findAll() throws BaseException {
        List<Place> placeList = placeService.findAll(Place.class);

        List<PlaceInfoType> placeInfoTypes = placeList.stream().map(this::toInfoType).collect(Collectors.toList());
        PlaceListResponse response = new PlaceListResponse();
        response.getList().addAll(placeInfoTypes);
        succesResponse(response);
        return response;
    }

    /**
     * Helyek lekérdezése világ azonosító alapján.
     *
     * @param worldId világ azonosító.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    public PlaceListResponse findByWorldId(String worldId) throws BaseException {
        if (StringUtils.isBlank(worldId)) {
            throw new BaseException("worldId is NULL");
        }
        List<Place> placeList = placeService.findByWorldId(worldId);
        List<PlaceInfoType> placeInfoTypes = placeList.stream().map(this::toInfoType).collect(Collectors.toList());
        PlaceListResponse response = new PlaceListResponse();
        response.getList().addAll(placeInfoTypes);
        succesResponse(response);
        return response;
    }

    private PlaceInfoType toInfoType(Place place) {
        PlaceInfoType placeInfoType = new PlaceInfoType();
        placeInfoType.setId(place.getId());
        placeInfoType.setName(place.getName());
        placeInfoType.setSector(place.getSector());
        placeInfoType.setWorldId(place.getWorld().getId());
        return placeInfoType;
    }
}
