package io.stepinto.demo.wildfly.swarm.world.service.action.place;

import io.stepinto.demo.wildfly.swarm.world.service.entity.Place;
import io.stepinto.demo.wildfly.swarm.world.service.entity.World;
import io.stepinto.demo.wildfly.swarm.world.service.service.PlaceService;
import io.stepinto.demo.wildfly.swarm.world.service.service.WorldService;
import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceRequest;
import io.stepinto.demo.wildfly_swarm.dto.place.place.CreatePlaceResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceInfoType;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceType;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Helyek létrehozását kezelő akció osztály.
 */
@RequestScoped
public class CreatePlaceAction extends BaseAction {

    @Inject
    private PlaceService placeService;

    @Inject
    private WorldService worldService;

    /**
     * Világon belüli hely létrehozása.
     *
     * @param createPlaceRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    @Transactional
    public CreatePlaceResponse createPlace(CreatePlaceRequest createPlaceRequest) throws BaseException {
        if (Objects.isNull(createPlaceRequest)) {
            throw new BaseException("createPlaceRequest is NULL");
        }
        PlaceType place = createPlaceRequest.getPlace();

        //Világ meglétének validálása.
        World world = worldService.findById(place.getWorldId(), World.class);

        Place entity = new Place();
        entity.setName(place.getName());
        entity.setSector(place.getSector());
        entity.setWorld(world);
        Place savedPlace = placeService.save(entity);

        CreatePlaceResponse response = new CreatePlaceResponse();
        PlaceInfoType placeInfoType = new PlaceInfoType();
        placeInfoType.setId(savedPlace.getId());
        placeInfoType.setName(savedPlace.getName());
        placeInfoType.setSector(savedPlace.getSector());
        placeInfoType.setWorldId(world.getId());
        response.setPlace(placeInfoType);
        succesResponse(response);
        return response;
    }

}
