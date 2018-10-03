package io.stepinto.demo.wildfly.swarm.guest.service.action;

import io.stepinto.demo.wildfly.swarm.guest.service.converter.GuestConverter;
import io.stepinto.demo.wildfly.swarm.guest.service.entity.Guest;
import io.stepinto.demo.wildfly.swarm.guest.service.service.GuestService;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestRequest;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.CreateGuestResponse;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestType;
import io.stepinto.wildfly.swarm.demo.common.action.BaseAction;
import io.stepinto.wildfly.swarm.demo.common.exception.BaseException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

/**
 * Vendég létrehozására szolgáló akció osztály.
 */
@RequestScoped
public class CreateGuestAction extends BaseAction {

    @Inject
    private GuestService guestService;

    /**
     * Új vendég létrehozása.
     *
     * @param createGuestRequest kérés objektum.
     * @return válasz objektum.
     * @throws BaseException ha valamilyen kivétel dobódik.
     */
    @Transactional
    public CreateGuestResponse createGuest(CreateGuestRequest createGuestRequest) throws BaseException {
        if (Objects.isNull(createGuestRequest)) {
            throw new BaseException("createGuestRequest is NULL");
        }
        GuestType guestType = createGuestRequest.getGuest();
        Guest savedGuest = guestService.save(GuestConverter.toEntity(guestType));

        CreateGuestResponse createGuestResponse = new CreateGuestResponse();
        createGuestRequest.setGuest(GuestConverter.toInfoType(savedGuest));
        succesResponse(createGuestResponse);
        return createGuestResponse;
    }

}
