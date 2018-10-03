package io.stepinto.demo.wildfly.swarm.guest.service.action;

import io.stepinto.demo.wildfly.swarm.guest.service.converter.GuestConverter;
import io.stepinto.demo.wildfly.swarm.guest.service.entity.Guest;
import io.stepinto.demo.wildfly.swarm.guest.service.service.GuestService;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestInfoType;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestListResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestResponse;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Vendégeket listázó akció osztály.
 */
@RequestScoped
public class GuestQueryAction extends BaseAction {

    @Inject
    private GuestService guestService;

    public GuestResponse findById(String id) throws BaseException {
        Guest story = guestService.findById(id, Guest.class);

        GuestResponse response = new GuestResponse();
        response.setGuest(GuestConverter.toInfoType(story));
        succesResponse(response);
        return response;
    }

    public GuestListResponse findAll() throws BaseException {
        List<Guest> worldList = guestService.findAll(Guest.class);

        List<GuestInfoType> worldInfoTypes = worldList.stream().map(GuestConverter::toInfoType).collect(Collectors.toList());
        GuestListResponse response = new GuestListResponse();
        response.getList().addAll(worldInfoTypes);
        succesResponse(response);
        return response;
    }
}
