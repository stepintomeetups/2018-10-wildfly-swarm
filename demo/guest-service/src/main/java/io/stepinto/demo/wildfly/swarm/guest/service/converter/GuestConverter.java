package io.stepinto.demo.wildfly.swarm.guest.service.converter;

import io.stepinto.demo.wildfly.swarm.guest.service.entity.Guest;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestInfoType;
import io.stepinto.demo.wildfly_swarm.dto.guest.guest.GuestType;
import io.stepinto.wildfly.swarm.demo.common.util.DateUtil;

/**
 * {@link Guest} és {@link GuestInfoType} közötti konvertálás.
 */
public final class GuestConverter {

    private GuestConverter(){}

    public static Guest toEntity(GuestType guestType) {
        Guest guest = new Guest();
        guest.setName(guestType.getName());
        guest.setPlaceId(guestType.getPlaceId());
        guest.setFirstVisitDate(DateUtil.getGregorianDate(guestType.getFirstVisitDate()));
        guest.setLastVisitDate(DateUtil.getGregorianDate(guestType.getLastVisitDate()));
        return guest;
    }


    public static GuestInfoType toInfoType(Guest guest) {
        GuestInfoType guestInfoType = new GuestInfoType();
        guestInfoType.setName(guest.getName());
        guestInfoType.setPlaceId(guest.getPlaceId());
        guestInfoType.setFirstVisitDate(DateUtil.getDate(guest.getFirstVisitDate()));
        guestInfoType.setLastVisitDate(DateUtil.getDate(guest.getLastVisitDate()));
        return guestInfoType;
    }
}
