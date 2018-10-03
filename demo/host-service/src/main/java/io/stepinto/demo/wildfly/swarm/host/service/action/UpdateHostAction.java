package io.stepinto.demo.wildfly.swarm.host.service.action;

import io.stepinto.demo.wildfly.swarm.host.service.converter.HostConverter;
import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.story.StoryRibbonService;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world.PlaceRibbonService;
import io.stepinto.demo.wildfly.swarm.host.service.ribbon.service.world.WorldRibbonService;
import io.stepinto.demo.wildfly.swarm.host.service.service.HostService;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostInfoType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostRequest;
import io.stepinto.demo.wildfly_swarm.dto.host.host.UpdateHostResponse;
import io.stepinto.demo.wildfly_swarm.dto.place.place.PlaceResponse;
import io.stepinto.demo.wildfly_swarm.dto.story.story.StoryResponse;
import io.stepinto.demo.wildfly_swarm.dto.world.world.WorldResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;

/**
 * Hoszt létrehozására szolgáló akció osztály.
 *
 * @author Nandor Holozsnyak
 */
@RequestScoped
public class UpdateHostAction extends BaseAction {

    @Inject
    private HostService hostService;

    @Inject
    private WorldRibbonService worldRibbonService;

    @Inject
    private PlaceRibbonService placeRibbonService;

    @Inject
    private StoryRibbonService storyRibbonService;

    /**
     * Gazda frissítése.
     *
     * @param updateHostRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    @Transactional
    public UpdateHostResponse updateHost(UpdateHostRequest updateHostRequest) throws BaseException {
        if (Objects.isNull(updateHostRequest)) {
            throw new BaseException("updateHostRequest is NULL");
        }
        HostInfoType hostType = updateHostRequest.getHost();
        Host originalHost = hostService.findById(hostType.getId(), Host.class);

        //Világ, hely, történeszál ellenőrzése
        unwrapRequest(worldRibbonService.getWorldById(hostType.getWorldId()), WorldResponse.class);
        unwrapRequest(placeRibbonService.getPlaceById(hostType.getPlaceId()), PlaceResponse.class);
        unwrapRequest(storyRibbonService.getStoryById(hostType.getStoryThreadId()), StoryResponse.class);
        Host host = HostConverter.toEntity(hostType);
        host.setLastUpdated(new Date());
        host.setCreated(originalHost.getCreated());
        Host savedHost = hostService.save(host);

        UpdateHostResponse response = new UpdateHostResponse();
        response.setHost(HostConverter.toHostInfoType(savedHost));
        succesResponse(response);
        return response;
    }

}
