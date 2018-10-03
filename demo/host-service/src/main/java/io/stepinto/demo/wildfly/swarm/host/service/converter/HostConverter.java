package io.stepinto.demo.wildfly.swarm.host.service.converter;

import io.stepinto.demo.wildfly.swarm.host.service.entity.Gender;
import io.stepinto.demo.wildfly.swarm.host.service.entity.Host;
import io.stepinto.demo.wildfly.swarm.host.service.entity.OperationSystem;
import io.stepinto.demo.wildfly_swarm.dto.host.host.GenderType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostInfoType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.HostType;
import io.stepinto.demo.wildfly_swarm.dto.host.host.OperationSystemType;
import io.stepinto.wildfly.swarm.demo.common.util.DateUtil;

/**
 * Gazdát konvertáló osztály.
 */
public final class HostConverter {

    private HostConverter() {
    }

    public static Host toEntity(HostType hostType) {
        Host host = new Host();
        host.setName(hostType.getName());
        host.setConsciousnessLevel(hostType.getConsciousnessLevel());
        host.setOperationSystem(OperationSystem.valueOf(hostType.getOperationSystem().name()));
        host.setGender(Gender.valueOf(hostType.getGender().name()));
        host.setStoryThreadId(hostType.getStoryThreadId());
        host.setWorldId(hostType.getWorldId());
        host.setPlaceId(hostType.getPlaceId());
        host.setActive(hostType.isActive());
        host.setCreated(DateUtil.getGregorianDate(hostType.getCreated()));
        host.setLastUpdated(DateUtil.getGregorianDate(hostType.getLastUpdated()));
        return host;
    }

    public static Host toEntity(HostInfoType hostInfoType) {
        Host host = toEntity((HostType) hostInfoType);
        host.setId(hostInfoType.getId());
        return host;
    }

    public static HostInfoType toHostInfoType(Host host) {
        HostInfoType hostInfoType = new HostInfoType();
        hostInfoType.setId(host.getId());
        hostInfoType.setName(host.getName());
        hostInfoType.setConsciousnessLevel(host.getConsciousnessLevel());
        hostInfoType.setOperationSystem(OperationSystemType.fromValue(host.getOperationSystem().name()));
        hostInfoType.setGender(GenderType.fromValue(host.getGender().name()));
        hostInfoType.setStoryThreadId(host.getStoryThreadId());
        hostInfoType.setActive(host.isActive());
        hostInfoType.setWorldId(host.getWorldId());
        hostInfoType.setPlaceId(host.getPlaceId());
        hostInfoType.setCreated(DateUtil.getDate(host.getCreated()));
        hostInfoType.setLastUpdated(DateUtil.getDate(host.getLastUpdated()));
        return hostInfoType;
    }

}
